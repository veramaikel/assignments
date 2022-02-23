package com.maximize.service;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.Scanner;

public class ConsoleService {
    private static final Logger log = Logger.getLogger(ConsoleService.class);
    public static final int MAX_SPACES = 70;

    private final Scanner scanner;

    public ConsoleService(){
        this(new Scanner(System.in));
    }
    public ConsoleService(Scanner scanner){
        this.scanner = scanner;
    }

    public void out(String msg, int times){
        System.out.print("\n\t");
        for (int i = 0; i < times; i++) {
            System.out.print(msg+" ");
        }
    }

    public void outInLine(String msg, String border){
        System.out.print(" "+border+msg+border+" ");
    }

    public int getIntByList(String msg, List<String> list, String defaultOption){
        int def = 0;
        if(defaultOption!=null) {
            System.out.print("\n\t"+def+"."+defaultOption+" (DEFAULT OPTION)");
        }
        else def = 1;
        for (int i = 0; i < list.size(); i++) {
            System.out.print("\n\t"+(i+1)+"."+list.get(i));
        }
        return getInt(msg, def, list.size());
    }

    public int getInt(String msg, int min, int max){
        int r = -1;
        while (r<min || r>max) {
            System.out.print("\n\t"+msg+" ("+min+"-"+max+") ");
            String invalidOpt = "\tINVALID OPTION";
            try {
                r = Integer.parseInt(scanner.nextLine());
                if(r<min || r>max) System.out.println(invalidOpt);
            } catch (Exception e) {
                log.debug(e.getMessage());
                System.out.println(invalidOpt);
            }
        }
        return r;
    }

    public String getString(String msg){
        System.out.print("\n\t"+msg);
        return scanner.nextLine();
    }
}
