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
public class NewClass1 {
    public static void main(String[] args){
    char input = '!';
        int ip = (int)input -32;
        int rand = 1;
        int q = 1;
        
        ip = (ip - rand) + (94 * q);
        
        ip = ip + 32;
        char output = (char)ip;
        System.out.println("ascii: "+ip);
        System.out.println("Char: "+output);
    }

}
