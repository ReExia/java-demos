package com.example.ngapi.controller;

import com.example.ngapi.model.BoardVO;
import com.example.ngapi.utils.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/api")
public class DataController {


    public static Logger logger = LoggerFactory.getLogger(DataController.class);

    @CrossOrigin(value = "http://localhost:4200")
    @PostMapping(value = "/save")
    @ResponseBody
    public Object saveData(HttpServletRequest request){
        logger.info("save requestData : {}", JsonMapper.obj2String(request.getParameterMap()));

        Map map = new HashMap();
        map.put("message", "请求成功");
        map.put("status", 1000);
        map.put("data", request.getParameterMap());

        return map;
    }

    @CrossOrigin(value = "http://localhost:4200")
    @PostMapping(value = "/post")
    public Object postData(HttpServletRequest request){
        logger.info("post requestData : {}", JsonMapper.obj2String(request.getParameterMap()));

        Map map = new HashMap();

        map.put("message","请求成功");
        map.put("status",1000);
        map.put("data",request.getParameterMap());

        return map;
    }


    @CrossOrigin(value = "http://localhost:4200")
    @DeleteMapping(value = "/delete/{id}")
    @ResponseBody
    public Object delete(@PathVariable("id") String id,HttpServletRequest request){
        logger.info("/delete/ requestId : {}", JsonMapper.obj2String(request.getParameterMap()));

        Map map = new HashMap<>();
        map.put("message","删除成功");
        map.put("status",1000);
        map.put("data",id);

        return map;
    }

    @CrossOrigin(value = "http://localhost:4200")
    @GetMapping(value = "/getVo/{id}")
    @ResponseBody
    public Object reponseSimple(@PathVariable("id") String id, HttpServletRequest request) {
        logger.info("/getVo/ requestId : {}", JsonMapper.obj2String(request.getParameterMap()));

        Map map = new HashMap<>();
        map.put("status",1000);
        map.put("data", getSimple());
        map.put("message","获取数据成功");

        return map;
    }

    @CrossOrigin(value = "http://localhost:4200")
    @GetMapping("/page/{pageNum}")
    @ResponseBody
    public Object responsePageData(@PathVariable("pageNum") Integer pageNum,HttpServletRequest request) {
        logger.info("page : {}", JsonMapper.obj2String(request.getParameterMap()));
        Map map = new HashMap<>();
        //请求页码
        map.put("pageNum", pageNum);
        //当前页
        map.put("currentPage", pageNum);
        //起始页
        map.put("startPage", 3);
        //结束页
        map.put("endPage", 8);
        //页数距离
        map.put("pageDistance", 5);
        //总页数
        map.put("totalPages", 10);
        //是不是最后一页
        map.put("isLastPage", pageNum == 10);
        //是不是第一页
        map.put("firstPage", pageNum == 1);
        //数据
        map.put("data", getListPage());
        //状态码
        map.put("status", 1000);
        //返回信息
        map.put("message", "获取数据成功");
        return map;
    }

    public List<BoardVO> getListPage() {
        List<BoardVO> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            BoardVO boardVO = new BoardVO(
                    uuid, "2",
                    "2812018212", "212"
                    , "2121921", "21"
                    , "21"
                    , "2121212"
            );
            list.add(boardVO);
        }
        return list;
    }

    public BoardVO getSimple() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return new BoardVO(
                uuid, "2",
                "2812018212", "212"
                , "2121921", "21"
                , "21"
                , "2121212"
        );
    }

    @CrossOrigin(value = "http://localhost:4200")
    @GetMapping(value = "/charts")
    @ResponseBody
    public Object chartsData(){

        List list = new ArrayList();

        for (int i = 0 ; i < 100 ; i++){
            list.add(randomData());
        }

        return list.toArray();
    }


    public int[] randomData(){
        Random random = new Random();


        int arr[] = new int[3];

        for (int i = 0 ; i < 3 ; i++){
            int result= random.nextInt(10);
            arr[i] = result;
        }
        return arr;
    }

}
