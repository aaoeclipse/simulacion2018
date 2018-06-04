package GUI;

import DB.SQL;
import DB.SQLimpl;
import utils.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {
    private JTextArea somethingSomethingDangerZoneTextArea;
    public JButton generateButton;
    private JPanel pnnel;
    public JButton CSVButton;
    SQL dbconnection = new SQLimpl();
    private int generos;
    private int mediaType;
    private int artistas;
    private int albums;
    private int cid;
    JFrame thisWindow;
    int reportsTo;
    int multiplicacion;
    double[] valores;
    // ----- RANDOM -----
    private String[] firstName = {"Ger", "Ser", "Bren", "Al", "San","Mar","Sal","Fer","Luis","Rord","Mel","Jos","Jor","Pedr","Carl"} ;
    private String[] lastName = {"ardo","ena","don","ejandra","tiago","iana","mon","nando","fer","igo","anie","eph","alita","los","tin"};
    private String[] titles = {"Dispair","Solitude","Monster", "Ugly","Manly","Grownup","House","Tree","Pure","Galaxy","Wars","Chicken","Dads","Homeless","Call","Car","Carl","Glass","Nice","Men","Women","Food","Destroyer","Final","Showdown"};
    private String[] direccion1 = {"11-","12-","13-","14-","15-","16-","17-","18-","19-","20-","21-","22-","23-","24-"};
    private String[] direccionzona = {"zona 1", "zona 2","zona 3","zona 4","zona 5","zona 6","zona 7","zona 8","zona 9","zona 10","zona 11","zona 12","zona 13","zona 14","zona 15"};
    private String[] direccion2 = {"30","31","32","33","34","35","36","37"};
    private String[] ciudades = {"Ciudad de Guatemala", "Quetzaltenango", "Escuintla","Puerto Barrios", "Coban", "Teculután", "Retalhuleu", "Barillas", "Jutiapa", "Poptún", "Huehuetenango", "Sacatepéquez"};
    private String[] estados = {"Alta Verapaz", "Baja Verapaz", "Chimaltenango", "Chiquimula", "El Progreso", "Esquintla", "Guatemala", "Izabal", "Jalapa", "Jutiapa"};
    private String[] emaillast = {"@gmail.com","@hotmail.com","@yahoo.com","@reddit.com"};
    private String[] generosArray = {"Electronic", "Popular", "Folk", "Rap", "House", "Rock", "Musical", "Punk", "Classical", "Salsa", "Pop", "Hip Hop", "Metal", "Funk", "Blues", "Jazz"};
    private String[] mediaTypeArray = {"MP3", "AVI", "MP4", "MKV", "MPEG"};
    private int currentTrack;


    private ArrayList<String> artistArray;
    ArrayList<String> albumArray;
    ArrayList<String> tracksArray;
    private Controller controller;


    public Main() {

        //window
        thisWindow = new JFrame("Login");
        thisWindow.setContentPane(pnnel);
        thisWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        thisWindow.pack();
        thisWindow.setVisible(true);


        Random random = new Random();
        albums=98;
        generos = generosArray.length;
        mediaType = mediaTypeArray.length;
        artistas = 70;
        artistArray = new ArrayList<>();
        albumArray = new ArrayList<>();
        tracksArray = new ArrayList<>();

        
        fillNames();
        fillAlbums();
        fillTracks();

        //dbconnection.Connect("postgres","spartan2012");

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controller.generate();
            }
            /*@Override
            public void actionPerformed(ActionEvent e) {
                dbconnection.deleteContent();
                String sql;
                sql = "";
                // GENEROS
                for (int i = 0; i < generosArray.length; i++){
                    // GENRES
                    sql = "INSERT INTO public.\"Genre\"(\n" +
                            "            \"GenreId\", \"Name\")\n" +
                            "    VALUES (" + i + ", \'"+ generosArray[i]+"\');\n";
                    dbconnection.insert(sql);
                }
                for (int i = 0; i < mediaTypeArray.length;i++){
                    // MediaType
                    sql = "INSERT INTO public.\"MediaType\"(\n" +
                            "            \"MediaTypeId\", \"Name\")\n" +
                            "    VALUES ("+ i + ", \'" +  mediaTypeArray[i]+ "\');\n";
                    dbconnection.insert(sql);
                }
                // ----- 1 -------
                for (int i = 0; i < 70;i++) {
                    // Artista
                    sql = "INSERT INTO public.\"Artist\"(" +
                            "    \"ArtistId\", \"Name\")" +
                            "    VALUES (" + i +", \'"+ artistArray.get(i)+"\');";
                    dbconnection.insert(sql);
                    // Playlist
                    sql = "INSERT INTO public.\"Playlist\"(\n" +
                            "            \"PlaylistId\", \"Name\")\n" +
                            "    VALUES ("+i+",\'"+nameGenerator2000()+"\' );";
                    dbconnection.insert(sql);
                }
                // ----- 2 -------
                for (int i = 0; i < 98; i++){
                    // Album
                    sql = "INSERT INTO public.\"Album\"(\n" +
                            "            \"AlbumId\", \"Title\", \"ArtistId\")\n" +
                            "    VALUES (" + i + ", \'" + albumArray.get(i) + "\', \'" + random.nextInt(artistas) + "\');\n";
                    dbconnection.insert(sql);
                }
                valores = new double[50];
                for (int i = 0; i < 50; i++){
                    valores[i] = 0.99 * (random.nextInt(4)+1);
                    // Track
                    sql = "INSERT INTO public.\"Track\"(\n" +
                            "            \"TrackId\", \"Name\", \"AlbumId\", \"MediaTypeId\", \"GenreId\", \"Composer\", \n" +
                            "            \"Milliseconds\", \"Bytes\", \"UnitPrice\")\n" +
                            "    VALUES ( " + i + ", \'"+tracksArray.get(i) +"\'," +  random.nextInt(albums)+ ", " + random.nextInt(mediaType)+ ", " + random.nextInt(generos) + ", \'" + nameGenerator2000() + "\', \'" + (60+random.nextInt(100))+
                            "\', " + (375000 + random.nextInt(1000))+", " + valores[i]+ " );\n";
                    dbconnection.insert(sql);
                    if (i == 0){
                        reportsTo = 0;
                    }else {
                        reportsTo = i;
                    }
                    // Employee
                    sql = "INSERT INTO public.\"Employee\"(\n" +
                            "            \"EmployeeId\", \"LastName\", \"FirstName\", \"Title\", \"ReportsTo\", \n" +
                            "            \"BirthDate\", \"HireDate\", \"Address\", \"City\", \"State\", \"Country\", \n" +
                            "            \"PostalCode\", \"Phone\", \"Fax\", \"Email\")\n" +
                            "    VALUES (" + i + ", \'" + nameGenerator2000() + "\', \'" + nameGenerator2000() + "\', \'" + titles[random.nextInt(titles.length)] +" "+ titles[random.nextInt(titles.length)] + "\', " + (reportsTo) + ", \n" +
                            "            \'" + (random.nextInt(28)+1) + "-" + (random.nextInt(11)+1) + "-"+ (1970 + random.nextInt(30)) + "\', " + "NOW()" + ", \'" + direccionMaker() + "\', \'" + random.nextInt(ciudades.length) + "\', \'" + random.nextInt(estados.length) + "\', \'" + "Guatemala" + "\'  , \n" +
                            "            \'" + numInts(4) + "\', \'" + numInts(8) + "\', \'" + numInts(8) + "\', \'" + nameGenerator2000() + emaillast[random.nextInt(emaillast.length)] + "\');";
                            dbconnection.insert(sql);
                }
                // ----- 3 -------
                for(int i = 0; i < 50; i++){
                    sql = "INSERT INTO public.\"Customer\"(\n" +
                            "            \"CustomerId\", \"FirstName\", \"LastName\", \"Company\", \"Address\", \n" +
                            "            \"City\", \"State\", \"Country\", \"PostalCode\", \"Phone\", \"Fax\", \"Email\", \n" +
                            "            \"SupportRepId\")\n" +
                            "    VALUES (" + i + ", \'" + nameGenerator2000() + "\', \'" + nameGenerator2000() + "\', \'" + nameGenerator2000() + " Co." + "\', \'" + direccionMaker() + "\', \n" +
                            "            \'" + ciudades[random.nextInt(ciudades.length)] + "\', \'" + estados[random.nextInt(estados.length)] + "\', \'" + "Guatemala" + "\', \'" + numInts(5) + "\', \'" + numInts(8) + "\', \'" + numInts(8) + "\', \'" + nameGenerator2000()+emaillast[random.nextInt(emaillast.length)] + "\', \n" +
                            "            \'" + random.nextInt(reportsTo) + "\');\n";
                    dbconnection.insert(sql);
                }
                // ----- 4 -------
                for(int i = 0; i < 3600; i++){
                     try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e1) {
                        System.err.println("sleep fail");
                    }
                    currentTrack = random.nextInt(valores.length);
                    multiplicacion = random.nextInt(4)+1;
                    // INVOICE
                    sql = "INSERT INTO public.\"Invoice\"(\n" +
                            "            \"InvoiceId\", \"CustomerId\", \"InvoiceDate\", \"BillingAddress\", \"BillingCity\", \n" +
                            "            \"BillingState\", \"BillingCountry\", \"BillingPostalCode\", \"Total\")\n" +
                            "    VALUES (" + i + ", " +
                            "" + random.nextInt(50) + ", " +
                            "\'" + "NOW()" + "\', " +
                            "\'" + direccionMaker() + "\', " +
                            "\'" + ciudades[random.nextInt(ciudades.length)] + "\', \n" +
                            "\'" + estados[random.nextInt(estados.length)] + "\', " +
                            "\'" + "Guatemala" + "\', " +
                            "\'" + numInts(5) + "\', " +
                            "" + valores[currentTrack]*multiplicacion + ");\n";
                    dbconnection.insert(sql);

                    //Invoice Line
                    sql = "INSERT INTO public.\"InvoiceLine\"(\n" +
                            "            \"InvoiceLineId\", \"InvoiceId\", \"TrackId\", \"UnitPrice\", \"Quantity\")\n" +
                            "    VALUES (" + i + "," +
                            "" + i + "," +
                            "" + currentTrack + "," +
                            "" + valores[currentTrack] + "," +
                            "\'" + multiplicacion + "\');\n";
                    dbconnection.insert(sql);

                    // CREAR CLIENTES AVECES
                    if((i%30) == 0){
                        cid = (50+(i/30));
                        sql = "INSERT INTO public.\"Customer\"(\n" +
                                "            \"CustomerId\", \"FirstName\", \"LastName\", \"Company\", \"Address\", \n" +
                                "            \"City\", \"State\", \"Country\", \"PostalCode\", \"Phone\", \"Fax\", \"Email\", \n" +
                                "            \"SupportRepId\")\n" +
                                "    VALUES (" + cid + ", \'" + nameGenerator2000() + "\', \'" + nameGenerator2000() + "\', \'" + nameGenerator2000() + " Co." + "\', \'" + direccionMaker() + "\', \n" +
                                "            \'" + ciudades[random.nextInt(ciudades.length)] + "\', \'" + estados[random.nextInt(estados.length)] + "\', \'" + "Guatemala" + "\', \'" + numInts(5) + "\', \'" + numInts(8) + "\', \'" + numInts(8) + "\', \'" + nameGenerator2000()+emaillast[random.nextInt(emaillast.length)] + "\', \n" +
                                "            \'" + random.nextInt(reportsTo) + "\');\n";
                        dbconnection.insert(sql);
                    }
                }
            }*/

        });
        CSVButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controller.CSVGenerator();
            }
        });
    }

    public String numInts(int length){
        Random rand = new Random();
        int[] numbers = new int[length];
        String toReturn = "";
        for (int i:numbers) {
            i = rand.nextInt(9);
            toReturn += ""+ i;
        }
        return toReturn;
    }
    private String nameGenerator2000() {
        Random rand = new Random();
        int f = rand.nextInt(14);
        int l = rand.nextInt(14);
        return firstName[f] + lastName[l];
    }
    private String direccionMaker(){
        Random rand = new Random();
        int f = rand.nextInt(direccion1.length);
        int l = rand.nextInt(direccion2.length);
        int z = rand.nextInt(direccionzona.length);
        return direccion1[f]+direccion2[l]+" "+direccionzona[z];
    }
    private void fillTracks(){
        tracksArray.add("For Those About To Rock (We Salute You)");
        tracksArray.add("Balls to the Wall");
        tracksArray.add("Fast As a Shark");
        tracksArray.add("Restless and Wild");
        tracksArray.add("Princess of the Dawn");
        tracksArray.add("Put The Finger On You");
        tracksArray.add("Lets Get It Up");
        tracksArray.add("Inject The Venom");
        tracksArray.add("Snowballed");
        tracksArray.add("Evil Walks");
        tracksArray.add("C.O.D.");
        tracksArray.add("Breaking The Rules");
        tracksArray.add("Night Of The Long Knives");
        tracksArray.add("Spellbound");
        tracksArray.add("Go Down");
        tracksArray.add("Dog Eat Dog");
        tracksArray.add("Let There Be Rock");
        tracksArray.add("Bad Boy Boogie");
        tracksArray.add("Problem Child");
        tracksArray.add("Overdose");
        tracksArray.add("Hell Aint A Bad Place To Be");
        tracksArray.add("Whole Lotta Rosie");
        tracksArray.add("Walk On Water");
        tracksArray.add("Love In An Elevator");
        tracksArray.add("Rag Doll");
        tracksArray.add("What It Takes");
        tracksArray.add("Dude (Looks Like A Lady)");
        tracksArray.add("Janies Got A Gun");
        tracksArray.add("Cryin");
        tracksArray.add("Amazing");
        tracksArray.add("Blind Man");
        tracksArray.add("Deuces Are Wild");
        tracksArray.add("The Other Side");
        tracksArray.add("Crazy");
        tracksArray.add("Eat The Rich");
        tracksArray.add("Angel");
        tracksArray.add("Livin On The Edge");
        tracksArray.add("All I Really Want");
        tracksArray.add("You Oughta Know");
        tracksArray.add("Perfect");
        tracksArray.add("Hand In My Pocket");
        tracksArray.add("Right Through You");
        tracksArray.add("Forgiven");
        tracksArray.add("You Learn");
        tracksArray.add("Head Over Feet");
        tracksArray.add("Mary Jane");
        tracksArray.add("Ironic");
        tracksArray.add("Not The Doctor");
        tracksArray.add("Wake Up");
        tracksArray.add("You Oughta Know (Alternate)");
        tracksArray.add("We Die Young");
        tracksArray.add("Man In The Box");
        tracksArray.add("Sea Of Sorrow");
        tracksArray.add("Bleed The Freak");
        tracksArray.add("I Cant Remember");
        tracksArray.add("Love, Hate, Love");
        tracksArray.add("It Aint Like That");
        tracksArray.add("Sunshine");
        tracksArray.add("Put You Down");
        tracksArray.add("Confusion");
        tracksArray.add("I Know Somethin (Bout You)");
        tracksArray.add("Real Thing");
        tracksArray.add("Desafinado");
        tracksArray.add("Garota De Ipanema");
        tracksArray.add("Samba De Uma Nota Só (One Note Samba)");
        tracksArray.add("Por Causa De Você");
        tracksArray.add("Ligia");
        tracksArray.add("Fotografia");
        tracksArray.add("Dindi (Dindi)");
        tracksArray.add("Se Todos Fossem Iguais A Você (Instrumental)");
        tracksArray.add("Falando De Amor");
        tracksArray.add("Angela");
        tracksArray.add("Corcovado (Quiet Nights Of Quiet Stars)");
        tracksArray.add("Outra Vez");
        tracksArray.add("O Boto (Bôto)");
        tracksArray.add("Canta, Canta Mais");
        tracksArray.add("Enter Sandman");
        tracksArray.add("Master Of Puppets");
        tracksArray.add("Harvester Of Sorrow");
        tracksArray.add("The Unforgiven");
        tracksArray.add("Sad But True");
        tracksArray.add("Creeping Death");
        tracksArray.add("Wherever I May Roam");
        tracksArray.add("Welcome Home (Sanitarium)");
        tracksArray.add("Cochise");
        tracksArray.add("Show Me How to Live");
        tracksArray.add("Gasoline");
        tracksArray.add("What You Are");
        tracksArray.add("Like a Stone");
        tracksArray.add("Set It Off");
        tracksArray.add("Shadow on the Sun");
        tracksArray.add("I am the Highway");
        tracksArray.add("Exploder");
        tracksArray.add("Hypnotize");
        tracksArray.add("Bringem Back Alive");
        tracksArray.add("Light My Way");
        tracksArray.add("Getaway Car");
        tracksArray.add("The Last Remaining Light");
        tracksArray.add("Your Time Has Come");
        tracksArray.add("Out Of Exile");
        tracksArray.add("Be Yourself");
        tracksArray.add("Doesnt Remind Me");
        tracksArray.add("Drown Me Slowly");
        tracksArray.add("Heavens Dead");
        tracksArray.add("The Worm");
        tracksArray.add("Man Or Animal");
        tracksArray.add("Yesterday To Tomorrow");
        tracksArray.add("Dandelion");
        tracksArray.add("#1 Zero");
        tracksArray.add("The Curse");
        tracksArray.add("Money");
        tracksArray.add("Long Tall Sally");
        tracksArray.add("Bad Boy");
        tracksArray.add("Twist And Shout");
        tracksArray.add("Please Mr. Postman");
        tracksArray.add("CMon Everybody");
        tracksArray.add("Rock N Roll Music");
        tracksArray.add("Slow Down");
        tracksArray.add("Roadrunner");
        tracksArray.add("Carol");
        tracksArray.add("Good Golly Miss Molly");
        tracksArray.add("20 Flight Rock");
        tracksArray.add("Quadrant");
        tracksArray.add("Snoopys search-Red baron");
        tracksArray.add("Spanish moss-A sound portrait-Spanish moss");
        tracksArray.add("Moon germs");
        tracksArray.add("Stratus");
        tracksArray.add("The pleasant pheasant");
        tracksArray.add("Solo-Panhandler");
        tracksArray.add("Do what cha wanna");
        tracksArray.add("Intro/ Low Down");
        tracksArray.add("13 Years Of Grief");
        tracksArray.add("Stronger Than Death");
        tracksArray.add("All For You");
        tracksArray.add("Super Terrorizer");
        tracksArray.add("Phoney Smile Fake Hellos");
        tracksArray.add("Lost My Better Half");
        tracksArray.add("Bored To Tears");
        tracksArray.add("A.N.D.R.O.T.A.Z.");
        tracksArray.add("Born To Booze");
        tracksArray.add("World Of Trouble");
        tracksArray.add("No More Tears");
        tracksArray.add("The Begining... At Last");
        tracksArray.add("Heart Of Gold");
        tracksArray.add("Snowblind");
        tracksArray.add("Like A Bird");
        tracksArray.add("Blood In The Wall");
        tracksArray.add("The Beginning...At Last");
        tracksArray.add("Black Sabbath");
        tracksArray.add("The Wizard");
        tracksArray.add("Behind The Wall Of Sleep");
        tracksArray.add("N.I.B.");
        tracksArray.add("Evil Woman");
        tracksArray.add("Sleeping Village");
        tracksArray.add("Warning");
        tracksArray.add("Wheels Of Confusion / The Straightener");
        tracksArray.add("Tomorrows Dream");
        tracksArray.add("Changes");
        tracksArray.add("FX");
        tracksArray.add("Supernaut");
        tracksArray.add("Snowblind");
        tracksArray.add("Cornucopia");
        tracksArray.add("Laguna Sunrise");
        tracksArray.add("St. Vitus Dance");
        tracksArray.add("Under The Sun/Every Day Comes and Goes");
        tracksArray.add("Smoked Pork");
        tracksArray.add("Body Counts In The House");
        tracksArray.add("Now Sports");
        tracksArray.add("Body Count");
        tracksArray.add("A Statistic");
        tracksArray.add("Bowels Of The Devil");
        tracksArray.add("The Real Problem");
        tracksArray.add("KKK Bitch");
        tracksArray.add("D Note");
        tracksArray.add("Voodoo");
        tracksArray.add("The Winner Loses");
        tracksArray.add("There Goes The Neighborhood");
        tracksArray.add("Oprah");
        tracksArray.add("Evil Dick");
        tracksArray.add("Body Count Anthem");
        tracksArray.add("Mommas Gotta Die Tonight");
        tracksArray.add("Freedom Of Speech");
        tracksArray.add("King In Crimson");
        tracksArray.add("Chemical Wedding");
        tracksArray.add("The Tower");
        tracksArray.add("Killing Floor");
        tracksArray.add("Book Of Thel");
        tracksArray.add("Gates Of Urizen");
        tracksArray.add("Jerusalem");
        tracksArray.add("Trupets Of Jericho");
        tracksArray.add("Machine Men");
        tracksArray.add("The Alchemist");
        tracksArray.add("Realword");
        tracksArray.add("First Time I Met The Blues");
        tracksArray.add("Let Me Love You Baby");
        tracksArray.add("Stone Crazy");
        tracksArray.add("Pretty Baby");
        tracksArray.add("When My Left Eye Jumps");
        tracksArray.add("Leave My Girl Alone");
        tracksArray.add("She Suits Me To A Tee");
        tracksArray.add("Keep It To Myself (Aka Keep It To Yourself)");
        tracksArray.add("My Time After Awhile");
        tracksArray.add("Too Many Ways (Alternate)");
        tracksArray.add("Talkin Bout Women Obviously");
        tracksArray.add("Jorge Da Capadócia");
        tracksArray.add("Prenda Minha");
        tracksArray.add("Meditação");
        tracksArray.add("Terra");
        tracksArray.add("Eclipse Oculto");
        tracksArray.add("Texto Verdade Tropical");
        tracksArray.add("Bem Devagar");
        tracksArray.add("Drão");
        tracksArray.add("Saudosismo");
        tracksArray.add("Carolina");
        tracksArray.add("Sozinho");
        tracksArray.add("Esse Cara");
        tracksArray.add("Mel");
        tracksArray.add("Linha Do Equador");
        tracksArray.add("Odara");
        tracksArray.add("A Luz De Tieta");
        tracksArray.add("Atrás Da Verd-E-Rosa Só Não Vai Quem Já Morreu");
        tracksArray.add("Vida Boa");
    }

    private void fillNames(){
        artistArray.add("AC/DC");
        artistArray.add("Accept");
        artistArray.add("Aerosmith");
        artistArray.add("Alanis Morissette");
        artistArray.add("Alice In Chains");
        artistArray.add("Antônio Carlos Jobim");
        artistArray.add("Apocalyptica");
        artistArray.add("Audioslave");
        artistArray.add("BackBeat");
        artistArray.add("Billy Cobham");
        artistArray.add("Black Label Society");
        artistArray.add("Black Sabbath");
        artistArray.add("Body Count");
        artistArray.add("Bruce Dickinson");
        artistArray.add("Buddy Guy");
        artistArray.add("Caetano Veloso");
        artistArray.add("Chico Buarque");
        artistArray.add("Djavan");
        artistArray.add("Eric Clapton");
        artistArray.add("Faith No More");
        artistArray.add("Falamansa");
        artistArray.add("Foo Fighters");
        artistArray.add("Frank Sinatra");
        artistArray.add("Godsmack");
        artistArray.add("Guns N Roses");
        artistArray.add("Incognito");
        artistArray.add("Iron Maiden");
        artistArray.add("James Brown");
        artistArray.add("Jamiroquai");
        artistArray.add("JET");
        artistArray.add("Jimi Hendrix");
        artistArray.add("Joe Satriani");
        artistArray.add("Jota Quest");
        artistArray.add("João Suplicy");
        artistArray.add("Judas Priest");
        artistArray.add("Legião Urbana");
        artistArray.add("Lenny Kravitz");
        artistArray.add("Lulu Santos");
        artistArray.add("Marillion");
        artistArray.add("Marisa Monte");
        artistArray.add("Marvin Gaye");
        artistArray.add("Men At Work");
        artistArray.add("Motörhead");
        artistArray.add("Motörhead & Girlschool");
        artistArray.add("Mônica Marianno");
        artistArray.add("Mötley Crüe");
        artistArray.add("Nirvana");
        artistArray.add("O Terço");
        artistArray.add("Olodum");
        artistArray.add("Os Paralamas Do Sucesso");
        artistArray.add("Ozzy Osbourne");
        artistArray.add("Philharmonia Orchestra & Sir Neville Marriner");
        artistArray.add("Academy of St. Martin in the Fields, Sir Neville Marriner & Thurston Dart");
        artistArray.add("Les Arts Florissants & William Christie");
        artistArray.add("The 12 Cellists of The Berlin Philharmonic");
        artistArray.add("Adrian Leaper & Doreen de Feis");
        artistArray.add("Roger Norrington, London Classical Players");
        artistArray.add("Charles Dutoit & LOrchestre Symphonique de Montréal");
        artistArray.add("Equale Brass Ensemble, John Eliot Gardiner & Munich Monteverdi Orchestra and Choir");
        artistArray.add("Kent Nagano and Orchestre de lOpéra de Lyon");
        artistArray.add("Julian Bream");
        artistArray.add("Martin Roscoe");
        artistArray.add("Göteborgs Symfoniker & Neeme Järvi");
        artistArray.add("Itzhak Perlman");
        artistArray.add("Michele Campanella");
        artistArray.add("Gerald Moore");
        artistArray.add("Mela Tenenbaum, Pro Musica Prague & Richard Kapp");
        artistArray.add("Emerson String Quartet");
        artistArray.add("C. Monteverdi, Nigel Rogers - Chiaroscuro; London Baroque; London Cornett & Sackbu");
        artistArray.add("Nash Ensemble");
        artistArray.add("Philip Glass Ensemble");
    }
    private void fillAlbums(){
        albumArray.add("For Those About To Rock We Salute You");
        albumArray.add("Balls to the Wall");
        albumArray.add("Restless and Wild");
        albumArray.add("Let There Be Rock");
        albumArray.add("Big Ones");
        albumArray.add("Jagged Little Pill");
        albumArray.add("Facelift");
        albumArray.add("Warner 25 Anos");
        albumArray.add("Plays Metallica By Four Cellos");
        albumArray.add("Audioslave");
        albumArray.add("Out Of Exile");
        albumArray.add("BackBeat Soundtrack");
        albumArray.add("The Best Of Billy Cobham");
        albumArray.add("Alcohol Fueled Brewtality Live! [Disc 1]");
        albumArray.add("Alcohol Fueled Brewtality Live! [Disc 2]");
        albumArray.add("Black Sabbath");
        albumArray.add("Black Sabbath Vol. 4 (Remaster)");
        albumArray.add("Body Count");
        albumArray.add("Chemical Wedding");
        albumArray.add("The Best Of Buddy Guy - The Millenium Collection");
        albumArray.add("Prenda Minha");
        albumArray.add("Sozinho Remix Ao Vivo");
        albumArray.add("Minha Historia");
        albumArray.add("Afrociberdelia");
        albumArray.add("Da Lama Ao Caos");
        albumArray.add("Acústico MTV [Live]");
        albumArray.add("Cidade Negra - Hits");
        albumArray.add("Na Pista");
        albumArray.add("Axé Bahia 2001");
        albumArray.add("BBC Sessions [Disc 1] [Live]");
        albumArray.add("Bongo Fury");
        albumArray.add("Carnaval 2001");
        albumArray.add("Chill: Brazil (Disc 1)");
        albumArray.add("Chill: Brazil (Disc 2)");
        albumArray.add("Garage Inc. (Disc 1)");
        albumArray.add("Greatest Hits II");
        albumArray.add("Greatest Kiss");
        albumArray.add("Heart of the Night");
        albumArray.add("International Superhits");
        albumArray.add("Into The Light");
        albumArray.add("Meus Momentos");
        albumArray.add("Minha História");
        albumArray.add("MK III The Final Concerts [Disc 1]");
        albumArray.add("Physical Graffiti [Disc 1]");
        albumArray.add("Sambas De Enredo 2001");
        albumArray.add("Supernatural");
        albumArray.add("The Best of Ed Motta");
        albumArray.add("The Essential Miles Davis [Disc 1]");
        albumArray.add("The Essential Miles Davis [Disc 2]");
        albumArray.add("The Final Concerts (Disc 2)");
        albumArray.add("Up An Atom");
        albumArray.add("Vinícius De Moraes - Sem Limite");
        albumArray.add("Vozes do MPB");
        albumArray.add("Chronicle, Vol. 1");
        albumArray.add("Chronicle, Vol. 2");
        albumArray.add("Cássia Eller - Coleção Sem Limite [Disc 2]");
        albumArray.add("Cássia Eller - Sem Limite [Disc 1]");
        albumArray.add("Come Taste The Band");
        albumArray.add("Deep Purple In Rock");
        albumArray.add("Fireball");
        albumArray.add("Knocking at Your Back Door: The Best Of Deep Purple in the 80s");
        albumArray.add("Machine Head");
        albumArray.add("Purpendicular");
        albumArray.add("Slaves And Masters");
        albumArray.add("Stormbringer");
        albumArray.add("The Battle Rages On");
        albumArray.add("Vault: Def Leppards Greatest Hits");
        albumArray.add("Outbreak");
        albumArray.add("Djavan Ao Vivo - Vol. 02");
        albumArray.add("Djavan Ao Vivo - Vol. 1");
        albumArray.add("Elis Regina-Minha História");
        albumArray.add("The Cream Of Clapton");
        albumArray.add("Unplugged");
        albumArray.add("Album Of The Year");
        albumArray.add("Angel Dust");
        albumArray.add("King For A Day Fool For A Lifetime");
        albumArray.add("The Real Thing");
        albumArray.add("Deixa Entrar");
        albumArray.add("In Your Honor [Disc 1]");
        albumArray.add("In Your Honor [Disc 2]");
        albumArray.add("One By One");
        albumArray.add("The Colour And The Shape");
        albumArray.add("My Way: The Best Of Frank Sinatra [Disc 1]");
        albumArray.add("Roda De Funk");
        albumArray.add("As Canções de Eu Tu Eles");
        albumArray.add("Quanta Gente Veio Ver (Live)");
        albumArray.add("Quanta Gente Veio ver--Bônus De Carnaval");
        albumArray.add("Faceless");
        albumArray.add("American Idiot");
        albumArray.add("Appetite for Destruction");
        albumArray.add("Use Your Illusion I");
        albumArray.add("Use Your Illusion II");
        albumArray.add("Blue Moods");
        albumArray.add("A Matter of Life and Death");
        albumArray.add("A Real Dead One");
        albumArray.add("A Real Live One");
        albumArray.add("Brave New World");
        albumArray.add("Dance Of Death");
    }

    public void addController(Controller controller) {
        this.controller = controller;
    }
}
