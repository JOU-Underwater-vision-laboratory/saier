package com.hhit.site.controller;

import com.hhit.site.domain.ContentDO;
import com.hhit.site.service.ContentService;
import com.hhit.common.config.Constant;
import com.hhit.common.controller.BaseController;
import com.hhit.common.utils.PageUtils;
import com.hhit.common.utils.Query;
import com.hhit.common.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/site/")
public class ContentController extends BaseController {
	@Autowired
    ContentService bContentService;

	@GetMapping()
	@RequiresPermissions("site:bContent:bContent")
	String bContent() {
		return "site/bContent/bContent";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("site:bContent:bContent")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<ContentDO> bContentList = bContentService.list(query);
		int total = bContentService.count(query);
		PageUtils pageUtils = new PageUtils(bContentList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("site:bContent:add")
	String add() {
		return "site/bContent/add";
	}

	@GetMapping("/edit/{cid}")
	@RequiresPermissions("site:bContent:edit")
	String edit(@PathVariable("cid") Long cid, Model model) {
		ContentDO bContentDO = bContentService.get(cid);
		model.addAttribute("bContent", bContentDO);
		return "site/bContent/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@RequiresPermissions("site:bContent:add")
	@PostMapping("/save")
	public R save(ContentDO bContent) {

		if (bContent.getAllowComment() == null) {
			bContent.setAllowComment(0);
		}
		if (bContent.getAllowFeed() == null) {
			bContent.setAllowFeed(0);
		}
		if(null==bContent.getType()) {
			bContent.setType("article");
		}
		bContent.setGtmCreate(new Date());
		bContent.setGtmModified(new Date());
		int count;
		if (bContent.getCid() == null || "".equals(bContent.getCid())) {
			count = bContentService.save(bContent);
		} else {
			count = bContentService.update(bContent);
		}
		if (count > 0) {
			return R.ok().put("cid", bContent.getCid());
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@RequiresPermissions("site:bContent:edit")
	@ResponseBody
	@RequestMapping("/update")
	public R update( ContentDO bContent) {
		bContent.setGtmCreate(new Date());
		bContentService.update(bContent);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequiresPermissions("site:bContent:remove")
	@PostMapping("/remove")
	@ResponseBody
	public R remove(Long id) {
		if (bContentService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@RequiresPermissions("site:bContent:batchRemove")
	@PostMapping("/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") Long[] cids) {
		bContentService.batchRemove(cids);
		return R.ok();
	}
}
