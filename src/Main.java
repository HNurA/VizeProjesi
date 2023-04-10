//Hilal Nur Albayrak 22120205056 Vize Projesi

import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

//Mail Gönderme
class Mail{
    DosyaOkuma kisiAl = new DosyaOkuma();
    String[][] elitUyeler = kisiAl.getElitUyeler();
    String[][] genelUyeler = kisiAl.getGenelUyeler();

    String emailAlan = "";    //Maili alan kişinin bilgileri

    //Gönderen kişinin bilgileri
    String email = "";
    String sifre = ""; //iki adımda doğrulama ile alınan uygulama şifresi

    // Kullanici kimlik bilgileri
    Authenticator dogrulama = new Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(email, sifre);
        }
    };
}

class MailGonder extends Mail{
    public void MailAt(int y){
        super.kisiAl.oku();
        int i=0;
        int sayac;
        Scanner input = new Scanner(System.in);

        //E-posta ayarları
        String host = "smtp.gmail.com";
        Properties ozellik = new Properties();
        ozellik.put("mail.smtp.host", host);
        ozellik.put("mail.smtp.port", "587");
        ozellik.put("mail.smtp.starttls.enable", "true");
        ozellik.put("mail.smtp.auth", "true");

        //Yeni protokol
        ozellik.setProperty("mail.smtp.ssl.protocols","TLSv1.2");

        //SMTP sunucusuna bağlanmak ve e-posta göndermek için gerekli olan oturum nesnesi
        Session oturum = Session.getInstance(ozellik, dogrulama);

        //Mail bilgilerini al
        String konu, icerik;
        System.out.println("Lütfen göndermek istediğiniz mailin konusunu giriniz:");
        konu = input.nextLine();
        System.out.println("Mail içeriğini giriniz:");
        icerik = input.nextLine();

        if(y == 1){
            sayac = kisiAl.elitSayisi;
            while(sayac!=0){
                emailAlan = elitUyeler[i][2];
                try {
                    // MimeMessage nesnesi olustur
                    MimeMessage mesaj = new MimeMessage(oturum);

                    // Gönderen kişi, Maili alan kişi bilgileri
                    mesaj.setFrom(new InternetAddress(email));
                    mesaj.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAlan));

                    //Konu
                    mesaj.setSubject(konu);

                    // Mail icerigi
                    mesaj.setText(icerik);

                    // Maili gönder
                    Transport.send(mesaj);
                } catch (MessagingException mex) {
                    mex.printStackTrace();
                }
                i++;
                sayac--;
            }
            i=0;
        }else if(y==2){
            sayac = kisiAl.genelSayisi;
            while(sayac!=0){
                emailAlan = genelUyeler[i][2];
                try {
                    // MimeMessage nesnesi olustur
                    MimeMessage mesaj = new MimeMessage(oturum);

                    // Gönderen kişi, Maili alan kişi bilgileri
                    mesaj.setFrom(new InternetAddress(email));
                    mesaj.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAlan));

                    //Konu
                    mesaj.setSubject(konu);

                    // Mail icerigi
                    mesaj.setText(icerik);

                    // Maili gönder
                    Transport.send(mesaj);
                } catch (MessagingException mex) {
                    mex.printStackTrace();
                }
                i++;
                sayac--;
            }
            i=0;
        }else{
            sayac = kisiAl.elitSayisi;
            while(sayac!=0){
                emailAlan = elitUyeler[i][2];
                try {
                    // MimeMessage nesnesi olustur
                    MimeMessage mesaj = new MimeMessage(oturum);

                    // Gönderen kişi, Maili alan kişi bilgileri
                    mesaj.setFrom(new InternetAddress(email));
                    mesaj.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAlan));

                    //Konu
                    mesaj.setSubject(konu);

                    // Mail icerigi
                    mesaj.setText(icerik);

                    // Maili gönder
                    Transport.send(mesaj);
                } catch (MessagingException mex) {
                    mex.printStackTrace();
                }
                i++;
                sayac--;
            }
            i=0;
            sayac = kisiAl.genelSayisi;
            while(sayac!=0){
                emailAlan = genelUyeler[i][2];
                try {
                    // MimeMessage nesnesi olustur
                    MimeMessage mesaj = new MimeMessage(oturum);

                    // Gönderen kişi, Maili alan kişi bilgileri
                    mesaj.setFrom(new InternetAddress(email));
                    mesaj.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAlan));

                    //Konu
                    mesaj.setSubject(konu);

                    // Mail icerigi
                    mesaj.setText(icerik);

                    // Maili gönder
                    Transport.send(mesaj);
                } catch (MessagingException mex) {
                    mex.printStackTrace();
                }
                i++;
                sayac--;
            }
        }
    }
}

class UyeEkle{
    public void ekleme(int x){
        String dosya = "Kullanıcılar.txt";
        Scanner giris = new Scanner(System.in);
        String ad="";
        String soyad="";
        String mail="";
        StringBuilder stringBuilder = new StringBuilder();
        try {
            // dosyayı okumak için BufferedReader nesnesi oluştur
            BufferedReader bufferedReader = new BufferedReader(new FileReader(dosya));
            String line;

            // Dosya satır satır okunana kadar döngüye gir
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        String metin = stringBuilder.toString();
        StringBuilder contents = new StringBuilder(metin);

        //Kullanıcıdan üye bilgilerini al
        System.out.println("İsmi giriniz:");
        ad = giris.nextLine();
        System.out.println("Soyismi giriniz:");
        soyad = giris.nextLine();
        System.out.println("Mail adresini giriniz:");
        mail = giris.nextLine();
        int index;

        //Kullanıcı seçimine göre gereken yere yazdır
        if(x == 1){
            index = metin.indexOf("#Genel Üyeler#")-2;
            contents.insert(index, ad+"    "+soyad+"    "+mail+"\n");
        }else{
            index = metin.indexOf("#Genel Üyeler#")+15;
            contents.insert(index, ad+"    "+soyad+"    "+mail);
        }

        // Yeni dosya oluşturmak için BufferedWriter nesnesi oluştur
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dosya));
            bufferedWriter.write(contents.toString());
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}

class DosyaOkuma{
    String[][] elitUyeler = new String[30][3];
    String[][] genelUyeler = new String[30][3];
    int elitSayisi=0, genelSayisi=0;
    public void oku(){
        int i=0, bayrak=0; //bayrak sıfır iken elite 1 iken genele eklesin diye
        genelSayisi=0;
        elitSayisi=0;
        try{
            File dosya = new File("Kullanıcılar.txt");
            Scanner scan = new Scanner(dosya);

            //Satır satır dosyayı oku
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                //Satırları tab karakterine göre böl
                String[] parts = line.split("    ");// \t yaptığımda hata verdi

                //Genele geçince bayrak değiştir
                if(line.equals("#Genel Üyeler#")){
                    bayrak = 1;
                    i = 0;
                }

                // Sadece üç sütuna sahip satırları işle
                if (parts.length == 3) {
                    String isim = parts[0];
                    String soyisim = parts[1];
                    String mail = parts[2];

                    if(bayrak == 0){
                        elitUyeler[i][0] = isim;
                        elitUyeler[i][1] = soyisim;
                        elitUyeler[i][2] = mail;
                        elitSayisi++;
                    }else if(bayrak == 1){
                        genelUyeler[i][0] = isim;
                        genelUyeler[i][1] = soyisim;
                        genelUyeler[i][2] = mail;
                        genelSayisi++;
                    }
                    i++;
                }

            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //Diğer classlardan uaşabilmek için getter kullan
    public String[][] getElitUyeler() {
        return elitUyeler;
    }
    public String[][] getGenelUyeler() {
        return genelUyeler;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner secme = new Scanner(System.in);
        MailGonder mail = new MailGonder();
        UyeEkle uye = new UyeEkle();
        DosyaOkuma okuyucu = new DosyaOkuma();

        int secim1, secim2;

        do{
            okuyucu.oku(); //her döngüde üyeleri okuyup diziye ekler
            //Menüyü yazdır
            System.out.println("------------------------------");
            System.out.println("1- Elit üye ekleme");
            System.out.println("2- Genel Üye ekleme");
            System.out.println("3- Mail Gönderme");
            System.out.println("4- Çıkış");
            secim1 = secme.nextInt();

            switch(secim1){
                case 1:
                    uye.ekleme(1);
                    break;
                case 2:
                    uye.ekleme(2);
                    break;
                case 3:
                    //İkinci menüyü yazdır
                    System.out.println("1- Elit üyelere mail");
                    System.out.println("2- Genel üyelere mail");
                    System.out.println("3- Tüm üyelere mail");
                    secim2 = secme.nextInt();
                    mail.MailAt(secim2);
                    System.out.println("E-posta basariyla gonderildi!");
                    break;
                case 4:
                    System.out.println("Programdan çıkılıyor.");
                    break;
                default:
                    System.out.println("Hatalı seçim!");
            }
        }while(secim1!=4);
    }
}
