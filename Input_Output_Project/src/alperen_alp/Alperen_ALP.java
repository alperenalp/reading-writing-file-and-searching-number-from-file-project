/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alperen_alp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author ALPEREN
 */
public class Alperen_ALP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean durum1 = true;
        boolean durum2 = true;
        int dizi1[] = dosyayiOku(durum1, durum2);
        durum1 = false;
        int dizi2[] = dosyayiOku(durum1, durum2);
        /*
        System.out.println("Dizi1'in Elemanlari:");
        for (int i = 0; i < dizi1.length; i++) {
            System.out.println(dizi1[i]);
        }
        System.out.println("\nDizi2'nin Elemanlari:");
        for (int i = 0; i < dizi2.length; i++) {
            System.out.println(dizi2[i]);
        }
         */
        
        siralamaYap(dizi1, dizi2);
        /*
        System.out.println("\ndizi1'in siralanmis elemanlari");
        for (int i = 0; i < dizi1.length; i++) {
            System.out.println(dizi1[i]);
        }
        System.out.println("\ndizi2'nin siralanmis elemanlari");
        for (int i = 0; i < dizi2.length; i++) {
            System.out.println(dizi2[i]);
        }
         */
        
        int dizi3[] = dizileriBirlestir(dizi1, dizi2);
        System.out.println("\ndizi3'un elemanlari");
        for (int i = 0; i < dizi3.length; i++) {
            System.out.println(dizi3[i]);
        }

        dosyayiYaz(dizi3);

        aramaYap();
    }

    public static int[] dosyayiOku(boolean durum1, boolean durum2) {
        int bosdizi[] = null;
        try {
            String okunan = null;
            File file1 = new File("diziler.txt");

            Scanner okuyucu = new Scanner(file1);
            int dizi1boyut = 0;
            while (okuyucu.hasNextLine()) {
                okunan = okuyucu.nextLine();
                if ("dizi2".equals(okunan)) {
                    break;
                }
                if (!"dizi1".equals(okunan)) {
                    dizi1boyut++;
                }
            }
            int dizi2boyut = 0;
            while (okuyucu.hasNextLine()) {
                okunan = okuyucu.nextLine();
                if (!"dizi2".equals(okunan)) {
                    dizi2boyut++;
                }
            }
            okuyucu.close();

            int[] dizi1 = new int[dizi1boyut];
            int[] dizi2 = new int[dizi2boyut];
            int i = 0;
            if (durum1) {
                Scanner okuyucu2 = new Scanner(file1);
                while (okuyucu2.hasNextLine()) {
                    okunan = okuyucu2.nextLine();
                    if ("dizi2".equals(okunan)) {
                        break;
                    }
                    if (!"dizi1".equals(okunan)) {
                        dizi1[i] = Integer.valueOf(okunan);
                        i++;
                    }
                }
                okuyucu2.close();
                return dizi1;
            }

            if (durum2) {
                Scanner okuyucu3 = new Scanner(file1);
                while (okuyucu3.hasNextLine()) {
                    okunan = okuyucu3.nextLine();
                    if ("dizi2".equals(okunan)) {
                        break;
                    }
                }
                while (okuyucu3.hasNextLine()) {
                    okunan = okuyucu3.nextLine();
                    dizi2[i] = Integer.valueOf(okunan);
                    i++;
                }
                okuyucu3.close();
                return dizi2;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Bir hata ile karsilasildi");
            e.printStackTrace();
        }
        return bosdizi;
    }

    public static int[] siralamaYap(int[] dizi1, int[] dizi2) {
        for (int i = 0; i < dizi1.length; i++) {
            int indis = i;
            for (int j = i + 1; j < dizi1.length; j++) {
                if (dizi1[j] < dizi1[indis]) {
                    indis = j;
                }
            }
            int eksayi = dizi1[indis];
            dizi1[indis] = dizi1[i];
            dizi1[i] = eksayi;
        }

        for (int i = 0; i < dizi2.length; i++) {
            int indis = i;
            for (int j = i + 1; j < dizi2.length; j++) {
                if (dizi2[j] < dizi2[indis]) {
                    indis = j;
                }
            }
            int eksayi = dizi2[indis];
            dizi2[indis] = dizi2[i];
            dizi2[i] = eksayi;
        }

        return dizi2;
    }

    public static int[] dizileriBirlestir(int[] dizi1, int[] dizi2) {
        int dizi3boyut = dizi1.length + dizi2.length;
        int[] dizi3 = new int[dizi3boyut];
        int ebsayi = 99999;
        for (int i = 0; i < dizi3boyut; i++) {
            int d1b = 0;
            int d2b = 0;
            int eksayi = 9999;
            int indis = 0;
            for (int j = d1b; j < dizi1.length; j++) {
                for (int k = d2b; k < dizi2.length; k++) {
                    if (eksayi > dizi2[k]) {
                        eksayi = dizi2[k];
                        indis = k;
                        dizi2[k] = ebsayi;
                    }
                    if (k == dizi2.length - 1) {
                        d2b = dizi2.length;
                    }
                }
                if (eksayi > dizi1[j]) {
                    dizi2[indis] = eksayi;
                    eksayi = dizi1[j];
                    dizi1[j] = ebsayi;
                }
            }
            dizi3[i] = eksayi;
        }
        return dizi3;
    }

    public static int[] dosyayiYaz(int[] dizi3) {
        try {
            File file1 = new File("siralanmisDiziler.txt");
            file1.createNewFile();
            FileWriter yazici = new FileWriter(file1);
            for (int i = 0; i < dizi3.length; i++) {
                yazici.write(String.valueOf(dizi3[i]) + "\n");
            }
            yazici.close();
        } catch (IOException e) {
            System.out.println("Yazma isleminde bir hata olustu");
            e.printStackTrace();
        }
        return dizi3;
    }

    public static void aramaYap() {
        try {
            String okunan = null;
            File file1 = new File("siralanmisDiziler.txt");
            Scanner scan = new Scanner(System.in);
            boolean durum = true;
            while (durum) {
                Scanner okuyucu = new Scanner(file1);
                int indis = 0;
                int aranan = 0;
                boolean kontrol = true;
                System.out.print("\nAranacak sayiyi giriniz: ");
                if (scan.hasNextInt()) {
                    aranan =  scan.nextInt();
                } else {
                    durum = false;
                    kontrol = false;
                }
                while (okuyucu.hasNextLine()) {
                    okunan = okuyucu.nextLine();
                    if (aranan == Integer.valueOf(okunan)) {
                        System.out.println("Aradiginiz sayi dosyada " + (indis + 1) + ". satirda bulunmaktadir");
                        kontrol = false;
                    }
                    indis++;
                }
                okuyucu.close();
                if (kontrol) {
                    System.out.println("Aradiginiz sayi bulunmadi");
                }
            }
            System.out.println("Program sonlandirildi.");

        } catch (IOException e) {
            System.out.println("Okuma2 isleminde bir hata olustu");
            e.printStackTrace();
        }
    }
}
