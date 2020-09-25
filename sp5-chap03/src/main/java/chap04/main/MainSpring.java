package chap04.main;


import chap04.spring.*;
import chap04.config.AppCtx;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import chap04.spring.exception.DuplicateMemberException;
import chap04.spring.exception.MemberNotFoundException;
import chap04.spring.exception.WrongPaswordException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 스프링 설정파일을 이용하는 경우
public class MainSpring {

    private static ApplicationContext ctx = null;

    private static void processNewCommand(String[] args) {

        if(args.length != 5) {
            printHelp();
            return;
        }

        // getBean("빈 객체 이름", 해당 빈 객체 타입) 은 유일성이 보장된 빈 객체를 리턴해 준다.
        // getBean(해당 빈 객체 타입) 은 타입 추론으로 빈 객체를 리턴해준다. 다른이름, 같은 타입의 빈이 2개 이상일 경우엔 Excpetion이다.
        MemberRegisterService regSvc = //assembler.getMemberRegisterService();
                                        ctx.getBean("memberRegisterService", MemberRegisterService.class);

        RegisterRequest req = new RegisterRequest();
        req.setEmail(args[1]);
        req.setName(args[2]);
        req.setPassword(args[3]);
        req.setConfirmPassword(args[4]);

        if(!req.isPasswordEqualToConfirmPassword()) {
            System.out.println("Does not match password.\n");
            return;
        }
        try {
            regSvc.regist(req);
            System.out.println("Has been registed.\n");
        }
        catch (DuplicateMemberException e) {
            System.out.println("It's Already existing email.\n");
        }
    }

    private static void processChangeCommand(String[] args) {
        if(args.length != 4) {
            printHelp();
            return;
        }

        ChangePasswordService changePasswordService =
                //assembler.getChangePasswordService();
                ctx.getBean("changePasswordService", ChangePasswordService.class);

        try {
            changePasswordService.changePassword(args[1], args[2], args[3]);
            System.out.println("Password has been successlly changed.\n");
        }
        catch (MemberNotFoundException e) {
            System.out.println("That email doesn't exist.\n");
        }
        catch (WrongPaswordException e) {
            System.out.println("Incorrect Email or Password.\n");
        }
    }
    private static void printHelp() {
        System.out.println();
        System.out.println("Use new [Email] [Name] [Password] [ConfirmPassword]");
        System.out.println("Use change [Email] [Current Password] [New Password]");
        System.out.println("Use info [Email]");
        System.out.println();
    }
    public static void main(String[] args) throws IOException {

        // Class.class는 해당 Class의 필드, 메서드 이름, 갯수 등의 구조적 정보를 알아내기위해 사용한다.
        ctx =
                // new AnnotationConfigApplicationContext(AppCtx.class);
                // new AnnotationConfigApplicationContext(AppCtx1.class, AppCtx2.class);
                new AnnotationConfigApplicationContext(AppCtx.class);

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("input: ");
            String command = reader.readLine();

            if(command.equalsIgnoreCase("exit")) {
                System.out.println("exit...");
                break;
            }

            if(command.startsWith("new")) {
                processNewCommand(command.split(" "));
                continue;
            }
            else if(command.startsWith("change ")) {
                processChangeCommand(command.split(" "));
                continue;
            }
            else if (command.equals("list")) {
                processListCommand();
                continue;
            } else if (command.startsWith("info ")) {
                processInfoCommand(command.split(" "));
                continue;
            } else if (command.equals("version")) {
                processVersionCommand();
                continue;
            }

            printHelp();
        }
    }

    private static void processVersionCommand() {
        VersionPrinter versionPrinter =
                ctx.getBean("versionPrinter", VersionPrinter.class);
        versionPrinter.print();
    }

    private static void processInfoCommand(String[] args) {
        if(args.length != 2) {
            printHelp();
            return;
        }

        MemberInfoPrinter infoPrinter =
                ctx.getBean("infoPrinter", MemberInfoPrinter.class);
        infoPrinter.printMemberInfo(args[1]);
    }

    private static void processListCommand() {
        MemberListPrinter listPrinter =
                ctx.getBean("memberListPrinter", MemberListPrinter.class);

        listPrinter.printAll();
    }
}
