package com.hhit.downloadRes.controller;

import com.hhit.common.annotation.Log;
import com.hhit.common.config.AppConfig;
import com.hhit.common.controller.BaseController;
import com.hhit.common.domain.FileDO;
import com.hhit.common.service.FileService;
import com.hhit.common.utils.*;
import com.hhit.downloadRes.domain.DownPerson;
import com.hhit.downloadRes.domain.SoftPicDO;
import com.hhit.downloadRes.domain.SoftwareDO;
import com.hhit.downloadRes.service.DownPersonService;
import com.hhit.downloadRes.service.SoftPicService;
import com.hhit.downloadRes.service.SoftwareService;
import com.hhit.system.domain.UserDO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

/**
 * 资源上载下载
 * @author  liujun
 * @date 2018-09-19 16:02:20
 */
@Controller
@Scope("prototype")
@RequestMapping("/downPage")
public class ResFillController extends BaseController {

	@Autowired
	private DownPersonService downPersonService;

    @Autowired
    private SoftwareService softwareService;

    @Autowired
    private FileService sysFileService;

    @Autowired
    private SoftPicService softPicService;

    private final ResourceLoader resourceLoader;

    @Autowired
    private AppConfig appConfig;

    @Autowired
    public ResFillController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


        @Log("访问Software文件页")
    @GetMapping()
    @RequiresPermissions("common:sysFile:software")
    String sysSoftFile(Model model) {
        Map<String, Object> params = new HashMap<>(16);
        return "common/file/softfile";
    }

    @Log("打开下载链接")
    @GetMapping("/oe/dynamicBE")
    String sysFileTest(Long fid, Model model) {
        SoftwareDO softwareDO = softwareService.findByFid(fid);
        model.addAttribute("software",softwareDO);
        return "common/file/dynamicBE";
    }


    @Log("返回文件关联图片")
    @GetMapping("/oe/{fid}")
    ResponseEntity showPic(@PathVariable Long fid) {
        try {
            return ResponseEntity.ok(resourceLoader.getResource(
                    Paths.get(appConfig.getUploadPath(), softPicService.findByFid(fid).getSoft_pic01()).toString()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
//
//        String filePath = bootdoConfig.getUploadPath() + softPicService.findByFid(fid).getSoft_pic01();
//
//        FileSystemResource file = new FileSystemResource(filePath);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getFilename()));
//        headers.add("Pragma", "no-cache");
//        headers.add("Expires", "0");
//
//        try {
//            return ResponseEntity
//                    .ok()
//                    .headers(headers)
//                    .contentLength(file.contentLength())
//                    .contentType(MediaType.parseMediaType("application/octet-stream"))
//                    .body(new InputStreamResource(file.getInputStream()));
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ResponseEntity.notFound().build();
//        }
    }
    @Log("文件列表查询")
    @ResponseBody
    @GetMapping("/list")
//    @RequiresPermissions("common:sysFile:sysFile")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        PageUtils pageUtils;
        UserDO userDO = getUser();
        if(userDO.getUserId()!=1){
                    // 查询列表数据
                    Query query = new Query(params);
                    query.put("createby",getUser().getUserId());
                    query.put("type",4);
                    List<FileDO> sysFileList = sysFileService.list(query);
                    int total_1 = sysFileService.count(query);

                    Query query_2 = new Query(params);

                    query_2.put("open",1);
                    query_2.put("type",4);
                    List<FileDO> sysFileList_2 = sysFileService.list(query_2);
//                    int total_2 = sysFileService.count(query_2);



                    Iterator it2 = sysFileList_2.iterator();

                        while (it2.hasNext()){
                            FileDO fileDO =(FileDO)it2.next();

                            if (  (fileDO).getCreateby().equals(userDO.getUserId()) ){
                                it2.remove();
                            }
                        }

                    sysFileList.addAll(sysFileList_2);
//                HashSet set = new HashSet(sysFileList);
//                sysFileList.clear();
//                sysFileList.addAll(set);
            pageUtils = new PageUtils(sysFileList, sysFileList.size());
        }else{
            Query query_admin = new Query(params);
            query_admin.put("type",4);
            List<FileDO> sysFileList_admin = sysFileService.list(query_admin);
            int total = sysFileService.count(query_admin);
            pageUtils = new PageUtils(sysFileList_admin,total);
        }
        return pageUtils;
    }
    @Log("轮播图查询")
    @ResponseBody
    @GetMapping("/oe/lists")
    public R portalsList(Model model) {

        Map<String,Object> map = new HashMap<String, Object>(16);
        map.put("type", 5);
        List<FileDO> lunbo = sysFileService.list(map);

        return R.ok().put("lunbo",lunbo);
    }
    @Log("上传软件配套图片")
    @ResponseBody
    @RequestMapping(value = "/u/show", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    R bindPic(HttpServletRequest request) {

        List<FileDO> list ;
        List<SoftwareDO> softlist = new ArrayList<SoftwareDO>(16);

        list = sysFileService.list(new HashMap<String, Object>(16));
        if(list.size()<1){
            return R.error(0, "暂时未提供软件包下载源，请联系工作人员");
        }else{
            SoftwareDO softwareDO;

            for (FileDO fileDO: list) {
                if(null!=fileDO.getOpen() && fileDO.getOpen() ){
                    softwareDO = softwareService.findByFid(fileDO.getId());
                    softwareDO.setSoftName(softwareDO.getSoftName().replace("/files/", ""));

                    softlist.add(softwareDO);
                }else{
                    continue;
                }
            }

            return R.ok().put("softlist",softlist);
        }

    }

    @Log("官网生成下载链接")
    @ResponseBody
    @RequestMapping(value = "/oe/show", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    R showFile(HttpServletRequest request) {

        List<SoftwareDO> list ;
        List<SoftwareDO> softlist = new ArrayList<SoftwareDO>(16);
        Map<String,Object>  map= new HashMap<String, Object>(16);
        map.put("softopen",true);
        softlist = softwareService.list(map);

            return R.ok().put("softlist",softlist);
//        }

    }


    @Log("编辑文件")
    @ResponseBody
    @RequestMapping(value = "/u/edit", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    R edit(@RequestParam("avatar_file") MultipartFile file,String fid, HttpServletRequest request) {

        String fileName;
        fileName = file.getOriginalFilename();

        if(fileName==null){
            return R.error();
        }
        SoftPicDO softPicDO;
        fid = fid.substring(fid.lastIndexOf("_")+1);
        Long f = Long.parseLong(fid);
        f = f / 11 ;
        try {
            fileName = FileUtil.renameToVersion(fileName, softwareService.findByFid(f).getSoftName());
            softPicDO = new SoftPicDO(f,fileName);
            FileUtil.uploadFile(file.getBytes(), appConfig.getUploadPath(), fileName);
            softPicService.save(softPicDO);

        } catch (Exception e) {
            System.out.println(e);
            return R.error(0,"上传错误，请联系管理员");
        }

        return R.ok();
    }


    @Log("上传文件")
    @ResponseBody
    @RequestMapping(value = "/u/upload", produces = "application/json;charset=UTF-8", method = {RequestMethod.POST,RequestMethod.GET})
    R upload(@RequestParam("avatar_file") MultipartFile file, HttpServletRequest request) {

        String fileName = file.getOriginalFilename();
        if(fileName==null){
            fileName="";
        }

        fileName = FileUtil.renameToVersion(fileName, request.getParameter("soft_version"),request.getParameter("soft_name"));

        FileDO fileDO = new FileDO( FileType.fileType(fileName), "/files/" + fileName, new Date(), false,getUser().getUserId(),false);

        try {
            FileUtil.uploadFile(file.getBytes(), appConfig.getUploadPath(), fileName);
        } catch (Exception e) {
            return R.error();
        }

        if (sysFileService.save(fileDO) > 0) {
            //FileDO sysFile = sysFileService.queryByUrlAndDate(fileDO.getUrl(),fileDO.getCreateDate());
            SoftwareDO software = new SoftwareDO(
                    new Date(),
                    fileDO.getId(),
                    request.getParameter("soft_name"),
                    request.getParameter("soft_introduction"),
                    request.getParameter("soft_keywords"),
                    request.getParameter("soft_function"),
                    request.getParameter("soft_author"),
                    request.getParameter("soft_authorEmail"),
                    request.getParameter("soft_version"),
                    "software_file",
                    ""
            );
//            software.setSoftCreateDate(new Date());
            software.setSoftCreateby(getUser().getUserId());
            software.setSoftopen(false);
            softwareService.save(software);

            return R.ok().put("fileName",fileDO.getUrl());
        }
        return R.error();
    }

    /**
     * 设置文件是否可开源
     */
    @Log("开放文件")
    @RequestMapping("/softopen")
    @ResponseBody
    @RequiresPermissions("site:software:open")
    public R softopen(Integer id) {

        SoftwareDO softwareDO = softwareService.get(id);
        String type = softwareDO.getSoftType();

        UserDO userDO= getUser();
        Boolean bol = false;
        if(type.equals("software_file")) {
            Long fid = softwareDO.getSoftFid();
            FileDO fileDO = sysFileService.get(fid);
            Long userid = fileDO.getCreateby();

            if (!(userid.equals(userDO.getUserId())) && (userDO.getUserId() != 1)) {
                return R.error("您无权开放其他用户上传的文件。");
            }
            // 设置文件是否官网公开
            bol = fileDO.getSoftopen();
            fileDO.setSoftopen(!bol);
            softwareDO.setSoftopen(!bol);

            sysFileService.update(fileDO);
        }else if(type.equals("software_url")){
            Long userid = softwareDO.getSoftCreateby();
            if (!(userid.equals(userDO.getUserId())) && (userDO.getUserId() != 1)) {
                return R.error("您无权开放其他用户上传的文件。");
            }
            // 设置是否官网公开
            bol = softwareDO.isSoftopen();
            softwareDO.setSoftopen(!bol);
            softwareDO.setSoftFid((long) 0);
        }
        softwareService.update(softwareDO);
        if(bol == true){
            return  R.ok("成功关闭发布");
        }else {
            return  R.ok("成功发布，请刷新门户网站预览");
        }
    }

    /**
     * @param downPerson 留存的信息
     * @return ResponseEntity<byte[]> 文件流
     * @author ray
     */
	@RequestMapping(value = "/d/download", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public String downFile(
	         DownPerson downPerson) throws IOException{
        if(downPerson==null){
            return null;// 抛出全局异常，表明没有提供信息
        }
        String person_name = downPerson.getPerson_name();
        DownPerson downPerson2 = downPersonService.find(person_name);

        if( downPerson2 == null ){
            downPersonService.save(downPerson);
        }else {
            downPersonService.update(downPerson);
        }

        return "redirect:/common/sysFile/download?id="+downPerson.getFid();
	}

}
