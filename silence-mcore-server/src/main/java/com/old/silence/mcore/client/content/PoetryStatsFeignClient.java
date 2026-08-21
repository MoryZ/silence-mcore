package com.old.silence.mcore.client.content;

import com.old.silence.mcore.vo.PoetryAchievementMcoreView;
import com.old.silence.mcore.vo.PoetryCategoryProgressMcoreView;
import com.old.silence.mcore.vo.PoetryRecentStudyMcoreView;
import com.old.silence.mcore.vo.PoetryStatsOverviewMcoreView;
import com.old.silence.mcore.vo.PoetryStatsWeeklyMcoreView;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.util.List;

/**
 * 统计页 Feign 客户端
 * <p>
 * content-service 需实现以下 5 个端点：
 * <ul>
 *   <li>GET /api/v1/poetryStats/overview — 学习总览</li>
 *   <li>GET /api/v1/poetryStats/weekly — 本周学习</li>
 *   <li>GET /api/v1/poetryStats/categoryProgress — 分类进度</li>
 *   <li>GET /api/v1/poetryStats/recentStudy — 最近学习记录</li>
 *   <li>GET /api/v1/poetryStats/achievements — 成就徽章</li>
 * </ul>
 * <p>
 * 游客（userId=0）所有端点返回空值/0。
 * BFF 聚合接口 /poetryStats/summary 内部调用这 5 个方法拼装。
 *
 * @author moryzang
 */
@FeignClient(name = "silence-content-service", contextId = "poetryStats", path = "/api/v1")
public interface PoetryStatsFeignClient {

    /**
     * 学习总览：累计学习时长/天数/已学诗词/笔记数
     *
     * @param userId 用户ID
     * @return 学习总览视图
     */
    @GetMapping("/poetryStats/overview")
    PoetryStatsOverviewMcoreView getOverview(@RequestParam BigInteger userId);

    /**
     * 本周学习：天数/新学/复习/时长
     * 周定义：周一 00:00:00 至当前
     *
     * @param userId 用户ID
     * @return 本周学习统计
     */
    @GetMapping("/poetryStats/weekly")
    PoetryStatsWeeklyMcoreView getWeekly(@RequestParam BigInteger userId);

    /**
     * 各分类进度（已学/总数/百分比）
     * 按用户已选学习计划关联的分类生成，无学习计划时返回空数组
     *
     * @param userId 用户ID
     * @return 分类进度列表
     */
    @GetMapping("/poetryStats/categoryProgress")
    List<PoetryCategoryProgressMcoreView> getCategoryProgress(@RequestParam BigInteger userId);

    /**
     * 最近学习记录列表
     *
     * @param userId 用户ID
     * @param limit  可选，默认 5，最大 20
     * @return 最近学习记录列表
     */
    @GetMapping("/poetryStats/recentStudy")
    List<PoetryRecentStudyMcoreView> getRecentStudy(@RequestParam BigInteger userId,
                                                     @RequestParam(defaultValue = "5") Integer limit);

    /**
     * 成就徽章列表（解锁状态）
     * 后端预定义成就规则（连续打卡/学习总数/笔记总数/收藏数等）
     *
     * @param userId 用户ID
     * @return 成就徽章列表
     */
    @GetMapping("/poetryStats/achievements")
    List<PoetryAchievementMcoreView> getAchievements(@RequestParam BigInteger userId);
}
