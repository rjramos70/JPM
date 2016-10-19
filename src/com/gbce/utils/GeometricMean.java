package com.gbce.utils;

import java.util.Scanner;

public class GeometricMean {

	/**
     * @param args
     */
    public static void main(String[] args) {
        GeometricMean gm = new GeometricMean();
        gm.findGM();

    }

    private void findGM() {
        int totNumb = 0;
       
        @SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter number of values:");
        totNumb = keyboard.nextInt();
        double mulNumb = 1;
        for (int i=0;i<totNumb;i++){
            int numb = 0;
            int itr = i+1;
            System.out.println(" enter the "+itr+"number : ");
            numb = keyboard.nextInt();
            mulNumb = mulNumb * numb;
        }
       
        System.out.println(" geometric mean "+ Math.sqrt(mulNumb));
    } 

}


