package chap03.spring;

import chap03.assembler.Assembler;
import chap03.spring.exception.DuplicateMemberException;
import chap03.spring.exception.MemberNotFoundException;
import chap03.spring.exception.WrongPaswordException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// POJO로 짠 assembler 설정파일을 이용하는 경우
// Create 와 Update만 구현하고 MainSpring으로 넘어갔음
public class Main {

    private static Assembler assembler = new Assembler();

    private static void processNewCommand(String[] args) {
        if(args.length != 5) {
            printHelp();
            return;
        }

        MemberRegisterService regSvc = assembler.getMemberRegisterService();

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
                assembler.getChangePasswordService();

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
        System.out.println();
    }
    public static void main(String[] args) throws IOException {
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

            printHelp();
        }
    }
}
