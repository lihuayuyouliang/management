package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.BasicInfoMapper;
import com.example.demo.dao.TraceMapper;
import com.example.demo.entity.*;
import com.example.demo.service.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class TraceServiceImpl extends ServiceImpl<TraceMapper, Trace> implements ITraceService {
    @Resource
    private IStdFieldQuoteService stdFieldQuoteService;
    @Resource
    private IBasicInfoService basicInfoService;
    @Resource
    private IModifyInfoService modifyInfoService;
    @Resource
    private IRelationService relationService;
    @Resource
    private IVariableFieldService variableFieldService;
    @Resource
    private ITraceService traceService;

    @Override
    public boolean saveUser(Trace trace) {
        return saveOrUpdate(trace);
    }

    public void searchFile() throws IOException {
        //初始目录
//        File dir = new File("C:\\Users\\18244\\Desktop\\1\\1");
        File dir = new File("E:\\多金融产品销售平台V20\\原子\\产品\\产品参数\\函数");
        System.out.println("------------------tool start--------------------");
        processDirByRecursive(dir,0);
        System.out.println("------------------tool end----------------------");
    }

    // 自定义查询条件，根据object_id字段查询记录是否存在
    public void saveOrUpdateBasedOnObjectId(Trace trace) {
        QueryWrapper<Trace> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("object_id", trace.getObjectId());
        Trace existingRecord = traceService.getOne(queryWrapper);
        if (existingRecord != null) {
            int id=existingRecord.getId();
            trace.setId(id);
            traceService.updateById(trace);
        } else {
            traceService.save(trace);
        }
    }

    public void processDirByRecursive(File dir,int level) throws IOException {
        System.out.println(dir.getAbsolutePath());
        //输出层次数
        for (int i = 0; i < level; i++) {
            System.out.print("-");
        }
        //获取这个目录下所有的子文件和子目录的数组。
        File[] files = dir.listFiles();
        //遍历这个数组，取出每个File对象
        if (files != null) {
            for (File f : files) {
                //判断这个File是否是一个文件，是：
                if (f.isFile()) {
                    Document doc = Jsoup.parse(f,"utf-8");
                    String[] basicResult = processBasic(doc.getElementsByTag("basic"));
                    processImport(basicResult[0], doc.getElementsByTag("import"));
                    processExport(basicResult[0], doc.getElementsByTag("export"));
                    processVariable(basicResult[0], doc.getElementsByTag("variable"));
                    // todo: extend
                    processModify(basicResult[0], doc.getElementsByTag("ModifyLog"));
                    processTrace(f, basicResult[0], basicResult[1], doc.getElementsByTag("code"));
                    System.out.println(f);
                } else {//否则就是一个目录，继续递归
                    //递归调用
                    processDirByRecursive(f,level+1);
                }
            }
        }
    }
    public String[] processBasic(Elements basicElement){
        Element element=basicElement.get(1);
        Attributes attributes=element.attributes();
        BasicInfo basicInfo=new BasicInfo();
        basicInfo.setObjectId(attributes.get("objectId"));
        basicInfo.setVersion(attributes.get("version"));
        basicInfo.setUpdateDate(attributes.get("updateDate"));
        basicInfo.setDescription(attributes.get("description"));
        basicInfo.setEnglishName(attributes.get("englishName"));
        basicInfo.setFlag(attributes.get("flag"));
        basicInfo.setResultSet(attributes.get("returnResultSet"));
        basicInfo.setCheckLicence(attributes.get("checkLicence"));
        basicInfo.setDataBase(attributes.get("dataBaseName"));
        basicInfo.setFunctionId(attributes.get("functionId"));
        basicInfo.setServiceNo(attributes.get("serviceNo"));
        basicInfo.setRequirementNo(attributes.get("requirementNo"));
        basicInfo.setRequirementType(attributes.get("requirementType"));
        basicInfo.setRequirementLevel(attributes.get("requirementLevel"));
        basicInfo.setAuditNeed(attributes.get("auditNeed"));
        basicInfo.setAuditType(attributes.get("auditType"));
        basicInfo.setAuditLevel(attributes.get("auditLevel"));
        basicInfo.setOperator(attributes.get("operator"));
        basicInfo.setSystemId(attributes.get("subSystemId"));
        basicInfo.setTimeout(attributes.get("timeout"));
        basicInfo.setTransMonitor(attributes.get("needTransMonitor"));
        basicInfo.setNoCall(attributes.get("noCallAS"));
        basicInfo.setDefaultCall(attributes.get("defaultCallAS"));
        basicInfo.setMicroService(attributes.get("genMicroService"));
        basicInfoService.saveOrUpdate(basicInfo);
        String[] basicResult=new String[2];
        basicResult[0]=attributes.get("objectId");
        basicResult[1]=attributes.get("version");
        return basicResult;
    }
    public void processImport(String objectId,Elements importElement){
        Element element=importElement.get(0);
        List<Node> nodes = element.childNodes();
        for(Node node:nodes){
            if (node instanceof TextNode){
                continue;
            }
//            if (node.getClass().toString().equals("class org.jsoup.nodes.TextNode")){
//                continue;
//            }
            Attributes attributes=node.attributes();
            StdFieldQuote stdFieldQuote=new StdFieldQuote();
            stdFieldQuote.setObjectId(objectId);
            stdFieldQuote.setName(attributes.get("name"));
            stdFieldQuote.setFlag(attributes.get("flag"));
            stdFieldQuote.setComment(attributes.get("comment"));
            stdFieldQuote.setImportOr(1);
            stdFieldQuoteService.saveOrUpdate(stdFieldQuote);
        }
    }
    public void processExport(String objectId,Elements exportElement){
        Element element=exportElement.get(0);
        List<Node> nodes = element.childNodes();
        for(Node node:nodes){
            if (node instanceof TextNode){
                continue;
            }
            Attributes attributes=node.attributes();
            StdFieldQuote stdFieldQuote=new StdFieldQuote();
            stdFieldQuote.setObjectId(objectId);
            stdFieldQuote.setName(attributes.get("name"));
            stdFieldQuote.setFlag(attributes.get("flag"));
            stdFieldQuote.setComment(attributes.get("comment"));
            stdFieldQuote.setImportOr(0);
            stdFieldQuoteService.saveOrUpdate(stdFieldQuote);
        }
    }
    public void processVariable(String objectId,Elements variableElement){
        Element element=variableElement.get(0);
        List<Node> nodes = element.childNodes();
        for(Node node:nodes){
            if (node instanceof TextNode){
                continue;
            }
            Attributes attributes=node.attributes();
            VariableField variableField=new VariableField();
            variableField.setObjectId(objectId);
            variableField.setName(attributes.get("name"));
            variableField.setType(attributes.get("type"));
            variableField.setDv(attributes.get("dv"));
            variableField.setParamName(attributes.get("cname"));
            variableField.setDescription(attributes.get("desc"));
            variableFieldService.saveOrUpdate(variableField);

        }
    }
    public void processModify(String objectId,Elements variableElement){
        Element element=variableElement.get(0);
        List<Node> nodes = element.childNodes();
        for(Node node:nodes){
            if (node instanceof TextNode){
                continue;
            }
            ModifyInfo modifyInfo=new ModifyInfo();
            modifyInfo.setObjectId(objectId);
            List<Node> secondNodes=node.childNodes();
            for(Node secondNode:secondNodes){
                if (secondNode instanceof Element) {
                    Element secondElement = (Element) secondNode;
                    switch (secondElement.tag().toString()){
                        case "position":
                            modifyInfo.setPosition(secondElement.text());
                            break;
                        case "date":
                            modifyInfo.setDate(secondElement.text());
                            break;
                        case "version":
                            modifyInfo.setVersion(secondElement.text());
                            break;
                        case "serialNumber":
                            modifyInfo.setSerialNumber(secondElement.text());
                            break;
                        case "user":
                            modifyInfo.setUser(secondElement.text());
                            break;
                        case "principal":
                            modifyInfo.setPrincipal(secondElement.text());
                            break;
                        case "cause":
                            modifyInfo.setCause(secondElement.text());
                            break;
                        case "content":
                            modifyInfo.setContent(secondElement.text());
                            break;
                        case "tester":
                            modifyInfo.setTester(secondElement.text());
                            break;
                        default:
                            break;
                    }
                }
            }
            modifyInfoService.saveOrUpdate(modifyInfo);
        }
    }
    public void processTrace(File file, String objectId,String version, Elements codeElement){
        Element element= codeElement.get(0);
        String textCode=element.text();
        Trace trace=new Trace();
        trace.setObjectId(objectId);
        trace.setName(file.getName());
        // todo: extend存什么?
        trace.setCode(textCode);
        trace.setFilePath(file.getAbsolutePath());
        trace.setVersion(version);
        traceService.saveOrUpdate(trace);
    }
}
