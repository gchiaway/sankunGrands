package com.akk.controller.api;

import com.akk.Vo.ArticleVo;
import com.akk.Vo.ResultVO;
import com.akk.dto.ArticleDto;
import com.akk.service.ApiMenuService;
import com.akk.service.ArticleService;
import com.akk.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 返回前端文章
 * Created by KHM
 * 2017/10/19 17:49
 */
@RestController
@RequestMapping("/api/articles")
public class ApiArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ApiMenuService apiMenuService;

    @RequestMapping(value = "/menu")
    @ResponseBody
    public ResultVO getMenu() {
        return ResultVOUtil.success(apiMenuService.getMenuList());
    }

    /**
     * 根据父节点查找父栏目
     * 暂不需要
     * @return
     */
    @RequestMapping(value="/{fid}",method = RequestMethod.GET)
    public ResultVO flanmu(@PathVariable("fid") Long fid,
                           ArticleDto articleDto){
        List<ArticleVo> articleVoList = articleService.ApisearchFlanmu(articleDto, fid);
        return ResultVOUtil.success(articleVoList);
    }

    /**
     * 根据父、子节点查找文章
     * @param fid
     * @param zid
     * @param articleDto
     * @return
     */
    @RequestMapping(value="/{fid}/{zid}",method = RequestMethod.GET)
    public ResultVO zlanmu(@PathVariable("fid") Long fid, @PathVariable("zid") Long zid,
                          ArticleDto articleDto){
        List<ArticleVo> articleVoList = articleService.ApisearchZlanmu(articleDto, fid, zid);
        return ResultVOUtil.success(articleVoList);
    }

    /**
     * 根据id查找文章
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResultVO findArticle(@PathVariable("id") Long id){
        ArticleVo articleVo = articleService.getById(id);
        return ResultVOUtil.success(articleVo);
    }
}
