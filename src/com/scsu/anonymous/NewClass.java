/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.scsu.anonymous;

/**
 *
 * @author Swathi
 */
public class NewClass {
    public static void main(String[] args){
        char input = '~';
        int ip = (int)input -32;
        int rand = 1;
        
        
        int ip1 = (ip + rand);
        int q = ip1/94;
        ip1 = ip1 % 94;
        ip1 = ip1+32;
        System.out.println("as: "+ip1);
        char output = (char)ip1;
        System.out.println("Quotient: "+q);
        System.out.println("ascii: "+ip1);
        System.out.println("Char: "+output);
        
    }

}
