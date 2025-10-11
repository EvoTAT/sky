package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@Slf4j
@RestController
@RequestMapping("/admin/dish")
@Api(tags = "菜品管理")
public class DishController {
    @Autowired
    private DishService dishService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 新增菜品
     *
     * @param dishDTO
     * @return
     */
    @PostMapping
    @ApiOperation("保存菜品")
    public Result save(@RequestBody DishDTO dishDTO) {
        log.info("新增菜品：{}", dishDTO);

        try {
            dishService.saveWithFlavor(dishDTO);
            // 清理缓存
            String key = "dish_" + dishDTO.getCategoryId();
            redisTemplate.delete(key);
            return Result.success();
        } catch (Exception e) {
            log.error("保存菜品失败", e);
            return Result.error("保存菜品失败");
        }
    }

    // 菜品分页查询
    @GetMapping("/page")
    @ApiOperation("菜品分页查询")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO) {
        log.info("菜品分页查询：{}", dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    // 删除菜品
    @DeleteMapping
    @ApiOperation("删除菜品")
    public Result delete(@RequestParam List<Long> ids) {
        log.info("删除菜品：{}", ids);
        dishService.delete(ids);
        // 清理缓存
        clearCache("dish_*" );
        return Result.success();
    }

    // 启用禁用菜品
    @PostMapping("/status/{status}")
    @ApiOperation("启用禁用菜品")
    public Result setStatus(@PathVariable Integer status, @RequestParam Long id) {
        log.info("设置菜品状态：status={}, id={}", status, id);
        dishService.startOrStop(status, id);
        // 清理缓存
        clearCache("dish_*" );
        return Result.success();
    }
// 根据ID获取菜品详情
    @GetMapping("/{id}")
    @ApiOperation("查询菜品")
    public Result<DishVO> getById(@PathVariable Long id) {
        log.info("根据id查询菜品：{}", id);
        DishVO dishVO = dishService.getByIdWithFlavor(id);
        return Result.success(dishVO);
    }

    @PutMapping
    @ApiOperation("修改菜品")
    public Result update(@RequestBody DishDTO dishDTO) {
        log.info("修改菜品：{}", dishDTO);
        dishService.updateWithFlavor(dishDTO);
        // 清理缓存
        clearCache("dish_*" );
        return Result.success();
    }

    // 查询菜品
    @GetMapping("/list")
    @ApiOperation("根据分类id查询菜品")
    public Result<List<Dish>> list(Long categoryId){
        List<Dish> list = dishService.list(categoryId);
        return Result.success(list);
    }

    private void clearCache(String key) {
        Set keys = redisTemplate.keys(key);
        redisTemplate.delete(keys);
    }
}
