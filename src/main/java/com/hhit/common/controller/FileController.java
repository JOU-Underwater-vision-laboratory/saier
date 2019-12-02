package com.hhit.common.controller;

import com.hhit.common.annotation.Log;
import com.hhit.common.config.AppConfig;
import com.hhit.common.domain.FileDO;
import com.hhit.common.service.FileService;
import com.hhit.common.utils.*;
import com.hhit.downloadRes.service.SoftwareService;

import com.hhit.system.domain.UserDO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * 文件上传
 * 
 * @author  liujun
 * @date 2018-09-19 16:02:20
 */
@Controller
@Scope("prototype")
@RequestMapping("/common/sysFile")
public class FileController extends BaseController {
    String prefix = "system/sysFile";

	@Autowired
	private FileService sysFileService;
    @Autowired
    private SoftwareService softwareService;

	@Autowired
	private AppConfig appConfig;

	@Log("访问文件页")
	@GetMapping()
	@RequiresPermissions("common:sysFile:sysFile")
	String sysFile(Model model) {
		Map<String, Object> params = new HashMap<>(16);
		return "common/file/file";
	}

    @Log("访问轮播图管理页")
    @GetMapping("/lunbo")
    @RequiresPermissions("common:sysFile:sysFile")
    String lunbo(Model model) {
        Map<String, Object> params = new HashMap<>(16);
        return "site/portals/lunbo";
    }


    @Log("跳转下载页")
    @GetMapping("/dynamicBE")
    //@RequiresPermissions("common:sysFile:sysFile")
    String sysFileTest() {
        Map<String, Object> params = new HashMap<>(16);
        return "redirect:/common/file/dynamicBE";
    }



    @Log("文件列表查询")
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("common:sysFile:sysFile")
	public PageUtils list(@RequestParam Map<String, Object> params) {
        PageUtils pageUtils;
        UserDO userDO = getUser();
        if(userDO.getUserId()!=1){
                // 查询列表数据
                Query query = new Query(params);
                query.put("createby",getUser().getUserId());
                List<FileDO> sysFileList = sysFileService.list(query);

                Query query_2 = new Query(params);
                query_2.put("open",1);
                List<FileDO> sysFileList_2 = sysFileService.list(query_2);

                Iterator it = sysFileList.iterator();
                Iterator it2 = sysFileList_2.iterator();
                while (it2.hasNext()){
                        FileDO fileDO =(FileDO)it2.next();
                        if ( fileDO.getCreateby().equals(userDO.getUserId()) ){
                            it2.remove();
                        }
                }
                sysFileList.addAll(sysFileList_2);

            pageUtils = new PageUtils(sysFileList, sysFileList.size());
        }else{
            Query query_admin = new Query(params);
            List<FileDO> sysFileList_admin = sysFileService.list(query_admin);
            int total = sysFileService.count(query_admin);
            pageUtils = new PageUtils(sysFileList_admin,total);
        }
		return pageUtils;
	}

	/**
	 * 添加
	 */
	@Log("添加文件")
	@GetMapping("/add")
	@RequiresPermissions("common:sysFile:add")
	String add() {
		return "common/file/operate/add";
	}

	/**
	 * 编辑
	 */
    @Log("编辑文件")
	@GetMapping("/edit")
	@RequiresPermissions("common:sysFile:edit")
    ModelAndView edit(Long id) {

		FileDO sysFile = sysFileService.get(id);

		return new ModelAndView("common/file/operate/edit","sysFile",sysFile);
	}

	/**
	 * 信息
	 */
    @Log("查看文件信息")
	@RequestMapping("/info/{id}")
	//@RequiresPermissions("common:info")
	public R info(@PathVariable("id") Long id) {
		FileDO sysFile = sysFileService.get(id);
		return R.ok().put("sysFile", sysFile);
	}

	/**
	 * 保存
	 */
    @Log("保存文件")
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("common:sysFile:save")
	public R save(FileDO sysFile) {
        sysFile.setCreateby(getUser().getUserId());
		if (sysFileService.save(sysFile) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
    @Log("更新文件")
	@RequestMapping("/update")
	@RequiresPermissions("common:sysFile:update")
	public R update(@RequestBody FileDO sysFile) {
        UserDO userDO= getUser();
        if(!( (sysFileService.get( sysFile.getId() ).getCreateby()).equals(userDO.getUserId()) ) || userDO.getUserId()!=1){
            return R.error("您无权编辑其他用户上传的文件。");
        }
		sysFileService.update(sysFile);

		return R.ok();
	}

    /**
     * 设置文件是否可开源
     */
    @Log("开放文件")
    @RequestMapping("/open")
    @ResponseBody
    @RequiresPermissions("common:sysFile:open")
    public R open(Long id) {
        UserDO userDO= getUser();
        if (!((sysFileService.get(id).getCreateby()).equals(userDO.getUserId() )) && (userDO.getUserId()!=1) ){
            return R.error("您无权开放其他用户上传的文件。");
        }
        FileDO sysFile = sysFileService.get(id);
        Boolean bol = sysFile.getOpen();
        sysFile.setOpen(!bol);

        sysFileService.update(sysFile);

        return R.ok();
    }



	/**
	 * 删除
	 */
    @Log("删除文件")
	@PostMapping("/remove")
	@ResponseBody
    @RequiresPermissions("common:sysFile:remove")
	public R remove(Long id, HttpServletRequest request) {
        UserDO userDO= getUser();
        FileDO fileDO = sysFileService.get(id);
        if (!((sysFileService.get(id).getCreateby()).equals(userDO.getUserId() )) && (userDO.getUserId()!=1) ){
            return R.error("您无权删除其他用户上传的文件。");
        }
		String fileName = appConfig.getUploadPath() + sysFileService.get(id).getUrl().replace("/files/", "");
		if (sysFileService.remove(id) > 0) {

			boolean b = FileUtil.deleteFile(fileName);
			if (!b) {
				return R.error("数据库记录删除成功，文件删除失败");
			}
			return R.ok();
		} else {
			return R.error();
		}
	}

	/**
	 * 删除
	 */
//	@PostMapping("/batchRemove")
//	@ResponseBody
//	@RequiresPermissions("common:sysFile:batchRemove")
//	public R remove(@RequestParam("ids[]") Long[] ids) {
//        if (sysFileService.get(id).getCreateby()!=getUser().getUserId()){
//            return R.error("您无权删除被人提交的文件");
//        }
//		sysFileService.batchRemove(ids);
//		return R.ok();
//	}

    @Log("上传文件")
	@ResponseBody
	@PostMapping("/upload")
	R upload(@RequestParam("file") MultipartFile file, HttpServletRequest request,Integer type) {

		String fileName = file.getOriginalFilename();
        UserDO userDO = getUser();
//		fileName = FileUtil.renameToDate(fileName);
		FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, new Date(),false,userDO.getUserId());

		try {
			FileUtil.uploadFile(file.getBytes(), appConfig.getUploadPath(), fileName);
		} catch (Exception e) {
			return R.error();
		}
		if ((type) != null){
		    sysFile.setType(type);
        }
		if (sysFileService.save(sysFile) > 0) {
			return R.ok().put("fileName",sysFile.getUrl());
		}
		return R.error();
	}

    /**
     * 下载
     */
    @Log("下载文件")
    @GetMapping("/download")
	ResponseEntity downFile(Long id) throws IOException {

        String filePath = appConfig.getUploadPath() + sysFileService.get(id).getUrl().replace("/files/", "");

        FileSystemResource file = new FileSystemResource(filePath);

        HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getFilename()));
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");

		return ResponseEntity
				.ok()
				.headers(headers)
				.contentLength(file.contentLength())
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.body(new InputStreamResource(file.getInputStream()));
	}
}
