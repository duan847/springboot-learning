package com.duan.video.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.duan.video.common.Constants;
import com.duan.video.common.Query;
import com.duan.video.pojo.entity.*;
import com.duan.video.pojo.vo.VideoDetailVO;
import com.duan.video.service.*;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 视频接口
 *
 * @author duanjw
 */
@Slf4j
@RestController
@RequestMapping("video")
public class VideoController {
    @Autowired
    private VideoService videoService;
    @Autowired
    private VideoRouteService videoRouteService;
    @Autowired
    private RouteUrlService routeUrlService;
    @Autowired
    private DictService dictService;
    @Autowired
    private VideoSortService videoSortService;
    @Autowired
    private CrawErrorService crawErrorService;
    /**
     * 爬取视频，使用多线程爬取，线程配置见
     *
     * @return
     * @see com.duan.video.config.ThreadPoolConfig
     * <p>
     * 从开始编号到截止编号
     * 开始编号必须
     * 截止编号默认为开始编号+1
     */
    @GetMapping("begin/{beginNo}/end/{endNo}")
    public String start(@PathVariable("beginNo") Integer beginNo, @PathVariable("endNo") Integer endNo) {
        log.info("爬取视频，从编号：{}到：{}", beginNo, endNo);
        if (endNo == null) {
            endNo = beginNo + 1;
        }
        for (int i = beginNo; i < endNo; i++) {
            videoService.crawByNo(i, null);
        }
        return "爬取视频，从：" + beginNo + "到：" + endNo;
    }

    /**
     * 分页查询视频-简单信息
     *
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("分页查询视频-简单信息。在url后追加过滤参数，包括——电影名称（name）")
    public Page<Video> selectSimplePage(@RequestParam Map<String, Object> params) {
        return videoService.selectSimplePage(new Query(params));
    }

    /**
     * 分页查询视频-简单信息
     *
     * @return
     */
    @GetMapping("/detail/page")
    @ApiOperation("分页查询视频-详细信息。在url后追加过滤参数，包括——电影名称（name）")
    public Page<VideoDetailVO> selectDetailPage(@RequestParam Map<String, Object> params) {
        return videoService.selectDetailPage(new Query(params));
    }

    /**
     * 根据id查看视频详细
     *
     * @return
     */
    @GetMapping("/{id}/detail")
    @ApiOperation("根据id查看视频详细")
    public VideoDetailVO getDetailById(@PathVariable Long id) {
        return videoService.getDetailById(id);
    }


    /**
     * 根据id分页查询视频线路
     *
     * @return
     */
    @ApiOperation("根据id分页查询视频线路")
    @GetMapping("/{id}/route/page")
    public IPage<VideoRoute> selectSimplePage(Page page, @PathVariable Long id) {
        return videoRouteService.selectByVideoIdPage(page, id);
    }

    /**
     * 根据id分页查询视频播放地址
     *
     * @return
     */
    @ApiOperation("根据id分页查询视频播放地址")
    @GetMapping("/{id}/url/page")
    public IPage<RouteUrl> selectUrlPage(Page page, @PathVariable Long id) {
        return routeUrlService.selectByVideoIdPage(page, id);
    }

    /**
     * 分页查询视频分类
     *
     * @return
     */
    @ApiOperation("分页查询视频分类")
    @GetMapping("/type/page")
    public IPage<Dict> selectTypePage(Page page) {
        return dictService.selectTypePage(page);
    }

    /**
     * 分页查询热映视频
     *
     * @return
     */
    @ApiOperation("分页查询热映视频")
    @GetMapping("/hot/page")
    public IPage<VideoDetailVO> selectHotPage(@RequestParam Map<String, Object> params) {
        return videoService.selectHotPage(new Query(params));
    }
    /**
     * 分页查询视频排行榜
     *
     * @return
     */
    @ApiOperation("分页查询视频排行榜")
    @GetMapping("/top/page")
    public IPage<VideoDetailVO> selectTop250Page(@RequestParam Map<String, Object> params) {
        return videoService.selectTop250Page(new Query(params));
    }

    /**
     * 更新热映电影
     * 定时：每天早上11点执行
     * @return
     */
    @Scheduled(cron = "0 0 11 * * ?")
    @ApiOperation("更新热映电影")
    @GetMapping("sort/hot")
    public boolean updateHotSort(){
        return videoSortService.updateByType(Constants.MOVIE_HOT, 1);
    }

    /**
     * 更新top250电影
     * 定时：每天早上11点5分执行
     * @return
     */
    @Scheduled(cron = "0 5 11 * * ?")
    @ApiOperation("更新top250电影")
    @GetMapping("sort/top250")
    public boolean updateTop250Sort(){
        return videoSortService.updateByType(Constants.MOVIE_TOP250, 1);
    }

    /**
     * 更新即将上映电影
     * @return
     */
    @ApiOperation("更新即将上映电影")
    @GetMapping("sort/coming")
    public boolean updateComingSort(){
        return videoSortService.updateByType(Constants.MOVIE_COMING, 1);
    }

    /**
     * 更新所有电影排序
     *
     * @return
     */
    @ApiOperation("更新所有电影排序")
    @GetMapping("sort/all")

    public boolean updateAllSort(){
        return videoSortService.updateAllSort();
    }

    /**
     * 更新所有视频时长
     * @return
     */
    @ApiOperation("更新所有视频时长")
    @GetMapping("url/filmlength")
    public boolean updateAllFilmLength(){
        long count = routeUrlService.count();
        Integer size = 100;
        for (int i = 0; i < count; i+=size) {
            routeUrlService.updateAllFilmLength(i, size);
        }
        return true;
    }

    /**
     * 根据id更新视频所有信息
     * @param id
     * @return
     */
    @ApiOperation("根据id更新视频所有信息")
    @PutMapping("{id}/allinfo")
    public boolean updateAllInfoById(@PathVariable Long id){
        return videoService.updateAllInfoById(id);
    }

    /**
     * 爬取最新视频
     * @return
     */
    @ApiOperation("爬取最新视频")
    @GetMapping("now")
    public boolean crawNow(){
        return videoService.crawNow();
    }

    /**
     * 更新待完结视频
     * @return
     */
    @ApiOperation("更新待完结视频")
    @GetMapping("incompletion")
    public boolean updateByIncompletion(){
        return videoService.updateByIncompletion();
    }

    /**
     * 根据爬取异常的内容重新爬取视频
     * @param content
     * @return
     */
    @ApiOperation("根据爬取异常的内容重新爬取视频")
    @GetMapping("error/content/{content}")
    public boolean updateByCrawError(@PathVariable String content) {
        List<CrawError> crawErrorList = crawErrorService.list(new QueryWrapper<CrawError>().lambda().eq(CrawError::getContent, content));
        crawErrorList.forEach( item -> {
            videoService.crawByNo(item.getVideoNo(),null);
            crawErrorService.removeById(item.getId());
        });
        return true;
    }
}
