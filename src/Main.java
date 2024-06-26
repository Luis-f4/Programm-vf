// Test
// Test 2
// Test 3

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Unternehmen vodafone = new Unternehmen("Vodafone");

    public static void main(String[] args) throws SQLException {
        System.out.println("Willkommen zur Vodafone Konsolenanwendung!");

        /*
        /////// Standartobjekte einfügen (start)


        //unternehmen
        Unternehmen vodafone = new Unternehmen("vodafone");
        UnternehmenDAO.addUnternehmen(vodafone);


        int UnternehmenID = UnternehmenDAO.getUnternehmenId("vodafone");

        //fuehurngskraft
        Fuehrungskraft fuehrungskraft = new Fuehrungskraft("Chef", 55, vodafone, UnternehmenID);
        FuehrungskraftDAO.addFuehrungskraft(fuehrungskraft);

        //Mitarbeiter
        Mitarbeiter mitarbeiter = new Mitarbeiter("mitarbeiter1", 25, "testabteilung", vodafone, UnternehmenID);
        MitarbeiterDAO.addMitarbeiter(mitarbeiter);


        //Kunde
        Kunde kunde = new Kunde("Kunde1", 30, vodafone, UnternehmenID);
        KundeDAO.addKunde(kunde);

        //MitarbeiterMarketing
        MitarbeiterMarketing mitarbeiterMarketing = new MitarbeiterMarketing("MaM1", 20, "Marketing", vodafone, UnternehmenID);
        MitarbeiterMarketingDAO.addMitarbeiterMarketing(mitarbeiterMarketing);

        //produkte

        Unternehmen unternehmen = UnternehmenDAO.getUnternehmenByName("vodafone");

        Produkt produkt = new Produkt(Produkt.Kategorie.INTERNET_PHONE, 50.00, "Beispiel Produkt3", unternehmen);
        ProduktDAO.addProdukt(produkt); //funktioniert


        //vertrag

        vertaegeDAO.addVertrag("Kunde1", "Beispiel Produkt3");


        /////// Standartobjekte einfügen (ende)
*/



        Unternehmen vodafone = UnternehmenDAO.getUnternehmenByName("vodafone");
        while (true) {
            System.out.println("\nBitte wählen Sie eine Option:");
            System.out.println("1. Mitarbeiter verwalten"); // Führungskraft
            System.out.println("2. Produkte verwalten"); // Mitarbeiter Marketing
            System.out.println("3. Kunden verwalten"); // Normaler Mitarbeiter
            System.out.println("4. Programm beenden");
            System.out.println("5. Als Kunde agieren");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (option) {
                case 1:
                    manageEmployees(); // Führungskraft funktioniert
                    break;
                case 2:
                    manageProducts(); // Mitarbeiter Marketing  Funktioniert
                    break;
                case 3:
                    manageCustomers(); // Mitarbeiter !!funktioniert allerdings kann man die SelltoCustomer funktion verbessern!!
                    break;
                case 4:
                    System.out.println("Programm wird beendet.");
                    return;
                case 5:
                    alsKundeagieren(); // !!!!!!!!!nicht Fertig!!!!!!!!!!!!!!!!!!
                    break;
                default:
                    System.out.println("Ungültige Option. Bitte erneut wählen.");
            }
        }


    }

    private static void manageProducts() throws SQLException {
        while (true) {
            displayAllProducts(); // Zeigt die Übersicht aller Produkte
            System.out.println("\nProduktverwaltung:");
            System.out.println("1. Produkt hinzufügen");
            System.out.println("2. Produkt entfernen");
            System.out.println("3. Zurück zum Hauptmenü oder schreib stop");


            String optionStr = scanner.nextLine();
            if (optionStr.equalsIgnoreCase("stop") ){
                return;
            }

            int option = Integer.parseInt(optionStr);
            //int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (option) {
                case 1:
                    addProduct(); //Funktioniert
                    break;
                case 2:
                    removeProduct(); //Funktioniert
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Ungültige Option. Bitte erneut wählen.");
            }
        }
    }




    private static void alsKundeagieren() throws SQLException {
        displayAllCustomers(); // Zeigt die Übersicht aller Kunden
        System.out.println("Gib den Namen des Kunden an, den du benutzen willst:");
        String name = scanner.nextLine();
        //Kunde kunde = findCustomerByName(name); alter code
        Kunde kunde = KundeDAO.getKundeByName(name);

        if (kunde != null) {
            while (true) {
                System.out.println("\nWas möchtest du als Kunde tun:");
                System.out.println("1. Monatliche Kosten ansehen");
                System.out.println("2. Vertrag abschließen");
                System.out.println("3. Vertrag kündigen");
                System.out.println("4. Zurück zum Hauptmenü oder schreib stop");


                String optionStr = scanner.nextLine();
                if (optionStr.equalsIgnoreCase("stop") ){
                    return;
                }

                int option = Integer.parseInt(optionStr);
                //int option = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (option) {
                    case 1:
                        KundeDAO.fillKundenVertraege(kunde, KundeDAO.getKundeId(kunde.getName()));
                        System.out.println("Monatliche Kosten: " + kunde.getMonatlicheKosten()); /// funktioniert
                        break;
                    case 2:
                        buyProdukt(kunde); /// Funktioniert
                        break;
                    case 3:
                        cancelContract(kunde); /// !!!!!!!!!!!!Funktioniert nicht!!!!!!!!!!!!!!!!!!!!!!!!!!
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Ungültige Option. Bitte erneut wählen.");
                }
            }
        } else {
            System.out.println("Kunde " + name + " nicht gefunden.");
        }
    }

    private static void manageCustomers() throws SQLException {
        while (true) {
            displayAllCustomers(); // Zeigt die Übersicht aller Kunden
            System.out.println("\nKundenverwaltung:");
            System.out.println("1. Kunde hinzufügen");
            System.out.println("2. Kunde entfernen");
            System.out.println("3. Produkt an Kunden verkaufen");
            System.out.println("4. Zurück zum Hauptmenü oder schreib stop");



            String optionStr = scanner.nextLine();
            if (optionStr.equalsIgnoreCase("stop") ){
                return;
            }

            int option = Integer.parseInt(optionStr);

            //int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (option) {
                case 1:
                    addCustomer(); // funktioniert
                    break;
                case 2:
                    removeCustomer(); // funktioniert
                    break;
                case 3:
                    sellProductToCustomer(); // funkioniert, aber kann man definitiv verbessern
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Ungültige Option. Bitte erneut wählen.");
            }
        }
    }

    private static void manageEmployees() throws SQLException { // Funktion als Führungskraft
        while (true) {
            displayAllEmployees(); // Zeigt die Übersicht aller Mitarbeiter
            System.out.println("\nMitarbeiterverwaltung:");
            System.out.println("1. Mitarbeiter einstellen");
            System.out.println("2. Mitarbeiter bearbeiten");
            System.out.println("3. Mitarbeiter entlassen");
            System.out.println("4. Zurück zum Hauptmenü oder schreib stop");


            String optionStr = scanner.nextLine();
            if (optionStr.equalsIgnoreCase("stop") ){
                return;
            }

            int option = Integer.parseInt(optionStr);
            //int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline character


            switch (option) {
                case 1:
                    hireEmployee(); // funktioniert
                    break;
                case 2:
                    editEmployee(); // funktioniert
                    break;
                case 3:
                    fireEmployee(); // funktioniert
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Ungültige Option. Bitte erneut wählen.");
            }
        }
    }

    private static void hireEmployee() throws SQLException {
        System.out.println("Bitte geben Sie den Namen des neuen Mitarbeiters ein:");
        String name = scanner.nextLine();
        System.out.println("Bitte geben Sie das Alter des neuen Mitarbeiters ein:");
        int alter = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.println("Bitte geben Sie die Abteilung des neuen Mitarbeiters ein:");
        String abteilung = scanner.nextLine();

        Mitarbeiter newEmployee = new Mitarbeiter(name, alter, abteilung, vodafone, UnternehmenDAO.getUnternehmenId("vodafone"));

        MitarbeiterDAO.addMitarbeiter(newEmployee);


        //Fuehrungskraft fuehrungskraft = new Fuehrungskraft("test", 20, vodafone); alter code
        //fuehrungskraft.MitarbeiterEinstellen(vodafone, newEmployee); alter code


        //vodafone.addMitarbeiter(newEmployee);



        if(newEmployee.getgehoertZuEinemUnternehmen()){
            System.out.println("Mitarbeiter " + name + " erfolgreich eingestellt.");
        }else{
            System.out.println("irgendwas ist schief gelaufen!!!!!!!");
        }
    }

    private static void editEmployee() throws SQLException {
        System.out.println("Bitte geben Sie den Namen des Mitarbeiters ein, den Sie bearbeiten möchten:");
        String name = scanner.nextLine();

        //Mitarbeiter mitarbeiter = findEmployeeByName(name);

        Mitarbeiter mitarbeiter = MitarbeiterDAO.getMitarbeiterByName(name);

        if (mitarbeiter != null) {
            System.out.println("Bitte geben Sie die neue Abteilung des Mitarbeiters ein:");
            String neueAbteilung = scanner.nextLine();

            //mitarbeiter.setAbteilung(neueAbteilung);

            MitarbeiterDAO.updateMitarbeiter(mitarbeiter, neueAbteilung);

            System.out.println("Mitarbeiter " + name + " erfolgreich bearbeitet.");
        } else {
            System.out.println("Mitarbeiter " + name + " nicht gefunden.");
        }
    }

    private static void fireEmployee() throws SQLException {
        System.out.println("Bitte geben Sie den Namen des Mitarbeiters ein, den Sie entlassen möchten:");
        String name = scanner.nextLine();

        //Mitarbeiter mitarbeiter = findEmployeeByName(name); alter code
        Mitarbeiter mitarbeiter = MitarbeiterDAO.getMitarbeiterByName(name);

        if (mitarbeiter != null) {
            MitarbeiterDAO.deleteMitarbeiterByName(name);

            //vodafone.removeMitarbeiter(mitarbeiter);
            //mitarbeiter.setgehoertZuEinemUnternehmen(false);

            System.out.println("Mitarbeiter " + name + " erfolgreich entlassen.");
        } else {
            System.out.println("Mitarbeiter " + name + " nicht gefunden.");
        }
    }

    private static Mitarbeiter findEmployeeByName(String name) {
        for (Mitarbeiter mitarbeiter : vodafone.getMitarbeiter()) {
            if (mitarbeiter.getName().equalsIgnoreCase(name)) {
                return mitarbeiter;
            }
        }
        return null;
    }

    private static void displayAllEmployees() throws SQLException {  // Funktioniert
        System.out.println("\nÜbersicht aller Mitarbeiter:");

        List<Mitarbeiter> mitarbeiterListe = MitarbeiterDAO.getAllMitarbeiter(); //funktioniert
        for (Mitarbeiter m : mitarbeiterListe) {
            System.out.println(m);
        }

        //for (Mitarbeiter mitarbeiter : vodafone.getMitarbeiter()) {
        //    System.out.println("Name: " + mitarbeiter.getName() + ", Abteilung: " + mitarbeiter.getAbteilung());
        //}


    }

    private static void displayAllProducts() throws SQLException { //funktioniert
        System.out.println("\nÜbersicht aller Produkte:");
        List<Produkt> produkteList = ProduktDAO.getAllProdukte(); //funktioniert


        for (Produkt produkt : produkteList) {
            System.out.println(produkt);
        }
    }

    private static void displayAllCustomers() throws SQLException { // funktioniert
        System.out.println("\nÜbersicht aller Kunden:");
        List<Kunde> kundenListe = KundeDAO.getAllKunden(); //funktioniert
        for (Kunde k : kundenListe) {
            System.out.println(k);
        }
    }

    private static void addProduct() throws SQLException {
        System.out.println("Bitte geben Sie die Kategorie des neuen Produkts ein (MOBILFUNK, INTERNET_PHONE, TV):");
        String kategorieStr = scanner.nextLine();

        Produkt.Kategorie kategorie = Produkt.Kategorie.valueOf(kategorieStr.toUpperCase());

        System.out.println("Bitte geben Sie den Preis des neuen Produkts ein:");
        double preis = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Bitte geben Sie den Namen des neuen Produkts ein:");
        String name = scanner.nextLine();

        vodafone.addProduct(kategorie, preis, name, vodafone);
        Produkt produkt = new Produkt(kategorie, preis, name, UnternehmenDAO.getUnternehmenByName("vodafone"));
        ProduktDAO.addProdukt(produkt);

        System.out.println("Produkt " + name + " erfolgreich hinzugefügt.");
    }

    private static void removeProduct() throws SQLException {
        System.out.println("Bitte geben Sie den Namen des Produkts ein, das Sie entfernen möchten:");
        String name = scanner.nextLine();
        System.out.println("Bitte geben Sie die Kategorie des Produkts ein (MOBILFUNK, INTERNET_PHONE, TV):");
        String kategorieStr = scanner.nextLine();
        Produkt.Kategorie kategorie = Produkt.Kategorie.valueOf(kategorieStr.toUpperCase());

        kategorieStr = kategorieStr.toUpperCase();

        //Produkt produkt = vodafone.getProdukteByName(name, kategorie); alter code

        Produkt produkt = ProduktDAO.getProduktByNameAndKategorie(name, kategorieStr);

        if (produkt != null) {
            vodafone.removeProduct(produkt);

            ProduktDAO.deleteProduktByName(name, kategorieStr);
            System.out.println("Produkt " + name + " erfolgreich entfernt.");
        } else {
            System.out.println("Produkt " + name + " nicht gefunden.");
        }
    }

    private static void addCustomer() throws SQLException {
        System.out.println("Bitte geben Sie den Namen des neuen Kunden ein:");
        String name = scanner.nextLine();
        System.out.println("Bitte geben Sie das Alter des neuen Kunden ein:");
        int alter = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        //Kunde newCustomer = new Kunde(name, alter); alter code
        Kunde newCustomer = new Kunde(name, alter, vodafone, UnternehmenDAO.getUnternehmenId("vodafone"));

        KundeDAO.addKunde(newCustomer);
        vodafone.addKunde(newCustomer);

        System.out.println("Kunde " + name + " erfolgreich hinzugefügt.");
    }

    private static void removeCustomer() throws SQLException {
        System.out.println("Bitte geben Sie den Namen des Kunden ein, den Sie entfernen möchten:");
        String name = scanner.nextLine();

        //Kunde kunde = findCustomerByName(name); alter code
        Kunde kunde = KundeDAO.getKundeByName(name);
        if (kunde != null) {
            vodafone.removeKunde(kunde);
            KundeDAO.deleteKundeByName(name);

            System.out.println("Kunde " + name + " erfolgreich entfernt.");
        } else {
            System.out.println("Kunde " + name + " nicht gefunden.");
        }
    }

    private static Kunde findCustomerByName(String name) {
        for (Kunde kunde : vodafone.getKunden()) {
            if (kunde.getName().equalsIgnoreCase(name)) {
                return kunde;
            }
        }
        return null;
    }

    private static void sellProductToCustomer() throws SQLException {
        System.out.println("Bitte geben Sie den Namen des Kunden ein, an den Sie ein Produkt verkaufen möchten:");
        String customerName = scanner.nextLine();

        //Kunde kunde = findCustomerByName(customerName); alter code
        Kunde kunde = KundeDAO.getKundeByName(customerName);

        if (kunde != null) {
            System.out.println("Bitte geben Sie den Namen des Produkts ein, das Sie verkaufen möchten:");
            String productName = scanner.nextLine();
            System.out.println("Bitte geben Sie die Kategorie des Produkts ein (MOBILFUNK, INTERNET_PHONE, TV):");
            String kategorieStr = scanner.nextLine();
            Produkt.Kategorie kategorie = Produkt.Kategorie.valueOf(kategorieStr.toUpperCase());

            Produkt produkt = vodafone.getProdukteByName(productName, kategorie);
            Produkt produktDB = ProduktDAO.getProduktByNameAndKategorie(productName, kategorieStr);
            //System.out.println("Daten produkt DB:" + produktDB);
            //System.out.println("Daten produkt lololol:" + produkt);

            if (produktDB != null) {
                System.out.println("Bitte geben Sie den Namen des Mitarbeiters ein, der das Produkt verkauft:");
                String employeeName = scanner.nextLine();
                //Mitarbeiter mitarbeiter = findEmployeeByName(employeeName); alter code
                Mitarbeiter mitarbeiter = MitarbeiterDAO.getMitarbeiterByName(employeeName);


                if (mitarbeiter != null && mitarbeiter.getgehoertZuEinemUnternehmen()) {
                    mitarbeiter.produktVerkaufen(kunde, produkt, vodafone);
                    vertaegeDAO.addVertrag(kunde.getName(), produktDB.getName());
                    System.out.println("Produkt " + productName + " erfolgreich an " + customerName + " verkauft.");
                } else {
                    System.out.println("Mitarbeiter " + employeeName + " ist nicht vorhanden oder gehört zu keinem Unternehmen.");
                }
            } else {
                System.out.println("Produkt " + productName + " nicht gefunden.");
            }
        } else {
            System.out.println("Kunde " + customerName + " nicht gefunden.");
        }
    }

    private static void buyProdukt(Kunde kunde) throws SQLException {
        System.out.println("Bitte geben Sie den Namen des Produkts ein, das Sie kaufen möchten:");
        String productName = scanner.nextLine();
        System.out.println("Bitte geben Sie die Kategorie des Produkts ein (MOBILFUNK, INTERNET_PHONE, TV):");
        String kategorieStr = scanner.nextLine();

        Produkt.Kategorie kategorie = Produkt.Kategorie.valueOf(kategorieStr.toUpperCase());
        Produkt produktDB = ProduktDAO.getProduktByNameAndKategorie(productName, kategorieStr);
        Produkt produkt = vodafone.getProdukteByName(productName, kategorie);

        if (produktDB != null) {
            displayAllEmployees();
            System.out.println("Bitte geben Sie den Namen des Mitarbeiters ein, der das Produkt verkauft:");
            String employeeName = scanner.nextLine();
            //Mitarbeiter mitarbeiter = findEmployeeByName(employeeName); alter code
            Mitarbeiter mitarbeiter = MitarbeiterDAO.getMitarbeiterByName(employeeName);

            if (mitarbeiter != null && mitarbeiter.getgehoertZuEinemUnternehmen()) {
                mitarbeiter.produktVerkaufen(kunde, produktDB, vodafone);
                vertaegeDAO.addVertrag(kunde.getName(), produktDB.getName());
                System.out.println("Produkt " + productName + " erfolgreich an " + kunde.getName() + " verkauft.");
                KundeDAO.fillKundenVertraege(kunde, KundeDAO.getKundeId(kunde.getName()));
                System.out.println("Aktuelle Verträge des Kunden: " + kunde.getVertraege()); // Debugging Ausgabe
            } else {
                System.out.println("Mitarbeiter " + employeeName + " ist nicht vorhanden oder gehört zu keinem Unternehmen.");
            }
        } else {
            System.out.println("Produkt " + productName + " nicht gefunden.");
        }
    }


    private static void cancelContract(Kunde kunde) throws SQLException {
        System.out.println("Bitte geben Sie den Namen des Produkts ein, das Sie kündigen möchten:");
        String productName = scanner.nextLine();

        Produkt zuKündigen = null;
        KundeDAO.fillKundenVertraege(kunde, KundeDAO.getKundeId(kunde.getName()));

        for (Produkt produkt : kunde.getVertraege()) {
            if (produkt.getName().equalsIgnoreCase(productName)) {
                zuKündigen = produkt;
                break;
            }
        }

        if (zuKündigen != null) {
            kunde.getVertraege().remove(zuKündigen);
            vertaegeDAO.deleteVertragByName(kunde.getName(), zuKündigen.getName());
            System.out.println("Produkt " + productName + " erfolgreich gekündigt.");
        } else {
            System.out.println("Produkt " + productName + " nicht gefunden.");
        }
    }



}