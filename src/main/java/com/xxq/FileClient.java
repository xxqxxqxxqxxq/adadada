package com.xxq;

import com.xxq.filemanager.bean.ArchivesInfo;
import com.xxq.filemanager.bean.DepartInfo;
import com.xxq.filemanager.bean.SysUserInfo;
import com.xxq.filemanager.config.ContextConfig;
import com.xxq.filemanager.entity.BorrowEntity;
import com.xxq.filemanager.gui.splashscreen.SplashConfig;
import com.xxq.filemanager.gui.view.*;
import com.xxq.filemanager.service.interfaceI.ArchivesService;
import com.xxq.filemanager.service.interfaceI.BorrowService;
import com.xxq.filemanager.springJavafxSupport.AbstractJavaFxApplicationSupport;
import com.xxq.filemanager.service.interfaceI.DepartService;
import com.xxq.filemanager.service.interfaceI.UserService;
import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName FileClient
 * @Description: TODO
 * @Author xxq
 * @Date 2019/12/27 9:07
 * @Version V1.0
 **/
@SpringBootApplication
@MapperScan(value = "com.xxq.filemanager.mapper")
public class FileClient extends AbstractJavaFxApplicationSupport {
    private static Logger logger=Logger.getLogger(FileClient.class);

    public static SysUserInfo sysUser;
    public static List<SysUserInfo> users;
    public static  List<DepartInfo> departInfoList = new ArrayList<>();
    public static  List<ArchivesInfo> archvivesList = new ArrayList<>();
    public static List<BorrowEntity> borrowList = new ArrayList<>();
    @Autowired
    UserService userService;
    @Autowired
    DepartService departService;
    @Autowired
    ArchivesService archivesService;
    @Autowired
    BorrowService borrowService;
    public static void main(String[] args) {
        new Thread(() -> {
            while (ContextConfig.context == null) {
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long time = System.currentTimeMillis();
            //从容器里获取一些类给一些赋值,为了方便的给一些工具类调用
            System.out.println(111);
            initialize();

        }).start();
        launch(FileClient.class, MainView.class, new SplashConfig(), args);
    }

    /**
     * 初始化界面参数
     */
    public static void initialize(){
        // 窗口初始化
        initStage();
    }

    @Bean
    public boolean loadDataFromDB() {
        List<DepartInfo> departInfos = departService.selectAllDepart();
        departInfoList.addAll(departInfos);
        List<ArchivesInfo> archivesInfoList = archivesService.queryAllArch();
        archvivesList.addAll(archivesInfoList);
        List<BorrowEntity> borrowEntities = borrowService.queryAll();
        borrowList.addAll(borrowEntities);
        return true;
    }
    /**
     * 所有窗口的初始化都在这里提前进行加载
     */
    private static void initStage() {
        logger.info("串口初始化");
        ContextConfig.context.getBean(MainView.class).getView();
        ContextConfig.context.getBean(LoginView.class).getView();
        ContextConfig.context.getBean(FileAddView.class).getView();
        ContextConfig.context.getBean(PersonMngView.class).getView();
        ContextConfig.context.getBean(FileParaView.class).getView();
        ContextConfig.context.getBean(UserMngView.class).getView();
        ContextConfig.context.getBean(RegisterUserView.class).getView();
        ContextConfig.context.getBean(UserParaView.class).getView();
        ContextConfig.context.getBean(FileMngView.class).getView();
        ContextConfig.context.getBean(BorrowFileView.class).getView();
        ContextConfig.context.getBean(AdminInfoView.class).getView();
        ContextConfig.context.getBean(FileView.class).getView();
        ContextConfig.context.getBean(MyBorrowView.class).getView();
        ContextConfig.context.getBean(DepartSearchView.class).getView();
    }

}
