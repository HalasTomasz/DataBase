-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Wersja serwera:               10.5.5-MariaDB - mariadb.org binary distribution
-- Serwer OS:                    Win64
-- HeidiSQL Wersja:              11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Zrzut struktury bazy danych invoice
CREATE DATABASE IF NOT EXISTS `invoice` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `invoice`;

-- Zrzut struktury procedura invoice.dodajFaktura
DELIMITER //
CREATE PROCEDURE `dodajFaktura`(IN firmaidd INT, IN klientidd INT, IN Dataa  DATE, OUT output INT)
BEGIN

DECLARE EXIT HANDLER FOR sqlwarning, sqlexception
BEGIN
ROLLBACK TO nothingDone;
SET output = 0;
END;


SET output = 0;
SET autocommit = 0;
start TRANSACTION;
SAVEPOINT nothingDone;
INSERT INTO faktura(firmaid, klientid, faktura.`Data`) 
VALUES (firmaidd, klientidd, Dataa ) ;
SET output = (SELECT MAX(FakturaID) FROM faktura) + 1;
commit;

END//
DELIMITER ;

-- Zrzut struktury procedura invoice.dodajFirma
DELIMITER //
CREATE PROCEDURE `dodajFirma`(IN pracownikidd INT, IN nazwafirmyy VARCHAR(90), IN adresfirmyy VARCHAR(90), IN nipfirmyy  VARCHAR(90), IN  telefonn  VARCHAR(90), OUT output INT)
BEGIN

DECLARE EXIT HANDLER FOR sqlwarning, sqlexception
BEGIN
ROLLBACK TO nothingDone;
SET output = 0;
END;


SET output = 0;
SET autocommit = 0;
start TRANSACTION;
SAVEPOINT nothingDone;
INSERT INTO firma(pracownikid, nazwafirmy, adresfirmy, nipfirmy, telefon) 
VALUES (pracownikidd, nazwafirmyy, adresfirmyy, nipfirmyy, telefonn) ;
SET output = 1;
commit;

END//
DELIMITER ;

-- Zrzut struktury procedura invoice.dodajKlient
DELIMITER //
CREATE PROCEDURE `dodajKlient`(IN nazwaa VARCHAR(90), IN nipklientaa VARCHAR(90), IN adress VARCHAR(90), IN emaill VARCHAR(90), IN telefonn VARCHAR(90), OUT output INT)
BEGIN

DECLARE EXIT HANDLER FOR sqlwarning, sqlexception
BEGIN
ROLLBACK TO nothingDone;
SET output = 0;
END;


SET output = 0;
SET autocommit = 0;
start TRANSACTION;
SAVEPOINT nothingDone;
INSERT INTO klienci(nazwa, nipklienta, adres, email, telefon) 
VALUES (nazwaa, nipklientaa, adress, emaill, telefonn);
SET output = 1;
commit;

END//
DELIMITER ;

-- Zrzut struktury procedura invoice.dodajListaProdukty
DELIMITER //
CREATE PROCEDURE `dodajListaProdukty`(IN produktidd INT, IN fakturaidd INT, IN iloscproduktuu INT, OUT output INT)
BEGIN

DECLARE EXIT HANDLER FOR sqlwarning, sqlexception
BEGIN
ROLLBACK TO nothingDone;
SET output = 0;
END;


SET output = 0;
SET autocommit = 0;
start TRANSACTION;
SAVEPOINT nothingDone;


INSERT INTO ProduktyNaFakturze(produktid, fakturaid, IloscProduktu) 
VALUES (produktidd, fakturaidd, iloscproduktuu);

UPDATE ProduktyNaFakturze l
INNER JOIN produkty p ON p.ProduktID = l.ProduktID
SET 
	l.Podatek = p.Podatek,
	l.Jednostka = p.Jednostka,
	l.Nazwa = p.NazwaProduktu,
	l.Cena = p.Cena,
	l.KwotaBrutto = ROUND(p.Cena * l.IloscProduktu * (1.00 + p.Podatek), 4),
	l.KwotaNetto = ROUND((p.Cena * l.IloscProduktu), 4)
WHERE
    l.ProduktID = produktidd;


    
    
SET output = 1;
commit;

END//
DELIMITER ;

-- Zrzut struktury procedura invoice.dodajPracownik
DELIMITER //
CREATE PROCEDURE `dodajPracownik`(IN loginn VARCHAR(90), IN hasloo VARCHAR(90), IN imiee VARCHAR(90), IN nazwiskoo VARCHAR(90), IN  uprawnieniaa INT, OUT output INT)
BEGIN

DECLARE EXIT HANDLER FOR sqlwarning, sqlexception
BEGIN
ROLLBACK TO nothingDone;
SET output = 0;
END;


SET output = 0;
SET autocommit = 0;
start TRANSACTION;
SAVEPOINT nothingDone;
INSERT INTO pracownicy(login, haslo, imie, nazwisko, uprawnienia) 
VALUES (loginn, sha1(hasloo), imiee, nazwiskoo, uprawnieniaa);
SET output = 1;
commit;

END//
DELIMITER ;

-- Zrzut struktury procedura invoice.dodajProdukt
DELIMITER //
CREATE PROCEDURE `dodajProdukt`(IN nazwaproduktuu VARCHAR(90), IN cenaa DOUBLE, IN jednostkaa VARCHAR(90), IN podatekk DOUBLE, OUT output INT)
BEGIN

DECLARE EXIT HANDLER FOR sqlwarning, sqlexception
BEGIN
ROLLBACK TO nothingDone;
SET output = 0;
END;


SET output = 0;
SET autocommit = 0;
start TRANSACTION;
SAVEPOINT nothingDone;
ALTER TABLE produkty AUTO_INCREMENT = 0;
INSERT INTO produkty(nazwaproduktu, cena, jednostka, podatek) 
VALUES (nazwaproduktuu, cenaa, jednostkaa, podatekk);
SET output = 1;

commit;

END//
DELIMITER ;

-- Zrzut struktury tabela invoice.faktura
CREATE TABLE IF NOT EXISTS `faktura` (
  `FakturaID` int(11) NOT NULL AUTO_INCREMENT,
  `FirmaID` int(11) NOT NULL,
  `KlientID` int(11) NOT NULL,
  `CalkowitaKwotaBrutto` double DEFAULT 0,
  `CalkowitaKwotaNetto` double DEFAULT 0,
  `Data` date NOT NULL,
  PRIMARY KEY (`FakturaID`),
  KEY `FirmaID` (`FirmaID`),
  KEY `KlientID` (`KlientID`),
  CONSTRAINT `faktura_ibfk_1` FOREIGN KEY (`FirmaID`) REFERENCES `firma` (`FirmaID`),
  CONSTRAINT `faktura_ibfk_2` FOREIGN KEY (`KlientID`) REFERENCES `klienci` (`KlientID`),
  CONSTRAINT `CONSTRAINT_1` CHECK (`CalkowitaKwotaNetto` >= 0.00),
  CONSTRAINT `CONSTRAINT_2` CHECK (`CalkowitaKwotaBrutto` >= 0.00)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Zrzucanie danych dla tabeli invoice.faktura: ~2 rows (około)
/*!40000 ALTER TABLE `faktura` DISABLE KEYS */;
INSERT INTO `faktura` (`FakturaID`, `FirmaID`, `KlientID`, `CalkowitaKwotaBrutto`, `CalkowitaKwotaNetto`, `Data`) VALUES
	(1, 3, 2, 10963.6788, 9748.24, '2000-02-12'),
	(2, 2, 1, 12.3, 10, '2020-08-01');
/*!40000 ALTER TABLE `faktura` ENABLE KEYS */;

-- Zrzut struktury tabela invoice.firma
CREATE TABLE IF NOT EXISTS `firma` (
  `FirmaID` int(11) NOT NULL AUTO_INCREMENT,
  `PracownikID` int(11) NOT NULL,
  `NazwaFirmy` varchar(90) NOT NULL,
  `AdresFirmy` varchar(90) NOT NULL,
  `NIPFirmy` varchar(90) NOT NULL,
  `Telefon` varchar(90) NOT NULL,
  PRIMARY KEY (`FirmaID`),
  KEY `PracownikID` (`PracownikID`),
  CONSTRAINT `firma_ibfk_1` FOREIGN KEY (`PracownikID`) REFERENCES `pracownicy` (`PracownikID`),
  CONSTRAINT `CONSTRAINT_1` CHECK (octet_length(`NIPFirmy`) = 10),
  CONSTRAINT `CONSTRAINT_2` CHECK (`NIPFirmy` regexp '^[0-9]+$')
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Zrzucanie danych dla tabeli invoice.firma: ~3 rows (około)
/*!40000 ALTER TABLE `firma` DISABLE KEYS */;
INSERT INTO `firma` (`FirmaID`, `PracownikID`, `NazwaFirmy`, `AdresFirmy`, `NIPFirmy`, `Telefon`) VALUES
	(1, 1, 'ziemniakiSellerGut', 'kuzniatalentow 1a', '4325674556', '111111111'),
	(2, 2, 'ziemniakiSellerGut2', 'kuzniatalentow 12', '7547865745', '222222222'),
	(3, 3, 'ziemniakiSellerGut', 'kuzniatalentow 1a', '5555555555', '333333333');
/*!40000 ALTER TABLE `firma` ENABLE KEYS */;

-- Zrzut struktury tabela invoice.klienci
CREATE TABLE IF NOT EXISTS `klienci` (
  `KlientID` int(11) NOT NULL AUTO_INCREMENT,
  `Nazwa` varchar(90) NOT NULL,
  `NIPKlienta` varchar(90) NOT NULL,
  `Adres` varchar(90) NOT NULL,
  `Email` varchar(90) NOT NULL,
  `Telefon` varchar(90) NOT NULL,
  PRIMARY KEY (`KlientID`),
  CONSTRAINT `CONSTRAINT_1` CHECK (octet_length(`NIPKlienta`) = 10),
  CONSTRAINT `CONSTRAINT_2` CHECK (`NIPKlienta` regexp '^[0-9]+$')
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Zrzucanie danych dla tabeli invoice.klienci: ~3 rows (około)
/*!40000 ALTER TABLE `klienci` DISABLE KEYS */;
INSERT INTO `klienci` (`KlientID`, `Nazwa`, `NIPKlienta`, `Adres`, `Email`, `Telefon`) VALUES
	(1, 'vietnamndealer1223', '2345678900', 'nbknow', 'helloinmdwon@lele.vt', '666777888'),
	(2, 'polandndealer1223', '9999999999', 'asdasd', 'helloinwarsaw@jej.pl', '112112112'),
	(3, 'russiamndealer2123', '7777777777', 'zxczxc', 'helloinmoscow@ejejej.def', '997997997');
/*!40000 ALTER TABLE `klienci` ENABLE KEYS */;

-- Zrzut struktury procedura invoice.logowanie
DELIMITER //
CREATE PROCEDURE `logowanie`(IN loginn VARCHAR(70), IN hasloo VARCHAR(90), OUT check_stat_text VARCHAR(180))
BEGIN
DECLARE passwordhass VARCHAR(180);
DECLARE pracownikid INT;
DECLARE upraw INT;

SET passwordhass = sha1(hasloo);
SELECT pracownicy.PracownikID INTO pracownikid FROM pracownicy WHERE pracownicy.login=loginn AND pracownicy.haslo=passwordhass;
SELECT pracownicy.Uprawnienia INTO upraw FROM pracownicy WHERE pracownicy.login=loginn AND pracownicy.haslo=passwordhass;

IF pracownikid IS NOT NULL THEN
	SET check_stat_text = CONCAT(upraw,"|",pracownikid);
ELSE
	SET check_stat_text = CONCAT("-1","|","-1");
END IF;


END//
DELIMITER ;

-- Zrzut struktury procedura invoice.poprawFaktura
DELIMITER //
CREATE PROCEDURE `poprawFaktura`(IN fakturaidd INT, IN Dataa DATE, OUT output INT)
BEGIN


DECLARE EXIT HANDLER FOR sqlwarning, sqlexception
BEGIN
ROLLBACK TO nothingDone;
SET output = 0;
END;



SET output = 0;
SET autocommit = 0;
start TRANSACTION;
SAVEPOINT nothingDone;


UPDATE faktura
SET 
    faktura.`Data` = Dataa
WHERE
   faktura.FakturaID = fakturaidd;
SET output = 1;
commit;


END//
DELIMITER ;

-- Zrzut struktury procedura invoice.poprawFirma
DELIMITER //
CREATE PROCEDURE `poprawFirma`(IN nazwafirmyy VARCHAR(90), IN adresfirmyy VARCHAR(90), IN nipfirmyy  VARCHAR(90), IN  telefonn  VARCHAR(90), OUT output INT)
BEGIN


DECLARE EXIT HANDLER FOR sqlwarning, sqlexception
BEGIN
ROLLBACK TO nothingDone;
SET output = 0;
END;



SET output = 0;
SET autocommit = 0;
start TRANSACTION;
SAVEPOINT nothingDone;


UPDATE firma 
SET 
    nazwafirmy = nazwafirmyy,
    adresfirmy= adresfirmyy,
    nipfirmy= nipfirmyy,
    telefon = telefonn
WHERE
    firma.NIPFirmy = nipfirmyy;
SET output = 1;
commit;


END//
DELIMITER ;

-- Zrzut struktury procedura invoice.poprawKlient
DELIMITER //
CREATE PROCEDURE `poprawKlient`(IN klientidd INT,IN nazwaa VARCHAR(90), IN nipklientaa VARCHAR(90), IN adress VARCHAR(90), IN emaill VARCHAR(90), IN telefonn VARCHAR(90), OUT output INT)
BEGIN

 

DECLARE EXIT HANDLER FOR sqlwarning, sqlexception
BEGIN
ROLLBACK TO nothingDone;
SET output = 0;
END;

 


SET output = 0;
SET autocommit = 0;
start TRANSACTION;
SAVEPOINT nothingDone;

 

UPDATE klienci
SET 
    nazwa = nazwaa,
    nipklienta =nipklientaa,
    adres =adress,
    email =emaill,
    telefon = telefonn
WHERE
    klienci.KlientID = klientidd;
    
SET output = 1;
commit;

 

END//
DELIMITER ;

-- Zrzut struktury procedura invoice.poprawListaProdukty
DELIMITER //
CREATE PROCEDURE `poprawListaProdukty`(IN pozycjaidd INT, IN nazwaa VARCHAR(90), IN cenaa DOUBLE, IN iloscproduktuu INT, IN jednostkaa VARCHAR(90), IN podatekk DOUBLE, OUT output INT)
BEGIN

DECLARE EXIT HANDLER FOR sqlwarning, sqlexception
BEGIN
ROLLBACK TO nothingDone;
SET output = 0;
END;


SET output = 0;
SET autocommit = 0;
start TRANSACTION;
SAVEPOINT nothingDone;

UPDATE produktynafakturze
SET 
	produktynafakturze.Nazwa = nazwaa,
	produktynafakturze.Cena= cenaa,
	produktynafakturze.IloscProduktu = iloscproduktuu,
	produktynafakturze.Jednostka = jednostkaa,
	produktynafakturze.Podatek = podatekk,
	produktynafakturze.KwotaNetto = ROUND((cenaa * iloscproduktuu), 4),
	produktynafakturze.KwotaBrutto = ROUND((cenaa * iloscproduktuu * (1.00 + podatekk)), 4)
WHERE
    produktynafakturze.PozycjaID = pozycjaidd;
    
SET output = 1;

commit;

END//
DELIMITER ;

-- Zrzut struktury procedura invoice.poprawPracownik
DELIMITER //
CREATE PROCEDURE `poprawPracownik`(IN pracownikidd INT,IN loginn VARCHAR(90), IN hasloo VARCHAR(90), IN imiee VARCHAR(90), IN nazwiskoo VARCHAR(90), IN  uprawnieniaa INT, OUT output INT)
BEGIN

 

DECLARE EXIT HANDLER FOR sqlwarning, sqlexception
BEGIN
ROLLBACK TO nothingDone;
SET output = 0;
END;

 


SET output = 0;
SET autocommit = 0;
start TRANSACTION;
SAVEPOINT nothingDone;

 

UPDATE pracownicy
SET 
    login = loginn,
    imie= imiee,
    nazwisko= nazwiskoo,
    uprawnienia = uprawnieniaa
WHERE
    pracownicy.PracownikID = pracownikidd;
    
UPDATE pracownicy
SET 
    haslo = hasloo
WHERE
    pracownicy.PracownikID = pracownikidd AND haslo != hasloo;
    
SET output = 1;

 

commit;

 

END//
DELIMITER ;

-- Zrzut struktury procedura invoice.poprawProdukt
DELIMITER //
CREATE PROCEDURE `poprawProdukt`(IN produktidd INT,IN nazwaproduktuu VARCHAR(90), IN cenaa DOUBLE, IN jednostkaa VARCHAR(90), IN podatekk DOUBLE, OUT output INT)
BEGIN


DECLARE EXIT HANDLER FOR sqlwarning, sqlexception
BEGIN
ROLLBACK TO nothingDone;
SET output = 0;
END;



SET output = 0;
SET autocommit = 0;
start TRANSACTION;
SAVEPOINT nothingDone;


UPDATE produkty
SET 
    nazwaproduktu = nazwaproduktuu,
    cena = cenaa,
    jednostka =jednostkaa,
    podatek =podatekk
WHERE
    produkty.ProduktID = produktidd;
SET output = 1;
commit;


END//
DELIMITER ;

-- Zrzut struktury tabela invoice.pracownicy
CREATE TABLE IF NOT EXISTS `pracownicy` (
  `PracownikID` int(11) NOT NULL AUTO_INCREMENT,
  `Login` varchar(90) NOT NULL,
  `Haslo` varchar(90) NOT NULL,
  `Imie` varchar(90) NOT NULL,
  `Nazwisko` varchar(90) NOT NULL,
  `Uprawnienia` int(11) NOT NULL,
  PRIMARY KEY (`PracownikID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Zrzucanie danych dla tabeli invoice.pracownicy: ~3 rows (około)
/*!40000 ALTER TABLE `pracownicy` DISABLE KEYS */;
INSERT INTO `pracownicy` (`PracownikID`, `Login`, `Haslo`, `Imie`, `Nazwisko`, `Uprawnienia`) VALUES
	(1, 'admin1', 'd033e22ae348aeb5660fc2140aec35850c4da997', 'Carl', 'Hejo', 3),
	(2, 'kierownik122', 'c03b3aa75cdf246f09a542ed8bb4ab64fc23ee92', 'karol', 'Cyna', 2),
	(3, 'pracownik1', 'c03b3aa75cdf246f09a542ed8bb4ab64fc23ee92', 'zxczxc', 'Zelazo', 1);
/*!40000 ALTER TABLE `pracownicy` ENABLE KEYS */;

-- Zrzut struktury tabela invoice.produkty
CREATE TABLE IF NOT EXISTS `produkty` (
  `ProduktID` int(11) NOT NULL AUTO_INCREMENT,
  `NazwaProduktu` varchar(90) NOT NULL,
  `Cena` double NOT NULL,
  `Jednostka` varchar(30) NOT NULL,
  `Podatek` double NOT NULL,
  PRIMARY KEY (`ProduktID`),
  CONSTRAINT `CONSTRAINT_1` CHECK (`Cena` >= 0.00),
  CONSTRAINT `CONSTRAINT_2` CHECK (`Podatek` >= 0.00 and `Podatek` <= 1.00)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Zrzucanie danych dla tabeli invoice.produkty: ~3 rows (około)
/*!40000 ALTER TABLE `produkty` DISABLE KEYS */;
INSERT INTO `produkty` (`ProduktID`, `NazwaProduktu`, `Cena`, `Jednostka`, `Podatek`) VALUES
	(1, '44444441', 1, ' szt.', 0.23),
	(2, 'ziemniakPremium22', 9, 'szt.', 0.23),
	(3, 'Batat22', 2, ' szt.', 0.23);
/*!40000 ALTER TABLE `produkty` ENABLE KEYS */;

-- Zrzut struktury tabela invoice.produktynafakturze
CREATE TABLE IF NOT EXISTS `produktynafakturze` (
  `PozycjaID` int(11) NOT NULL AUTO_INCREMENT,
  `ProduktID` int(11) NOT NULL,
  `FakturaID` int(11) NOT NULL,
  `Nazwa` varchar(90) DEFAULT 'None',
  `Cena` double DEFAULT 0,
  `IloscProduktu` int(11) NOT NULL,
  `Podatek` double DEFAULT 0,
  `Jednostka` varchar(30) DEFAULT 'szt',
  `KwotaNetto` double DEFAULT 0,
  `KwotaBrutto` double DEFAULT 0,
  PRIMARY KEY (`PozycjaID`),
  KEY `FakturaID` (`FakturaID`),
  KEY `ProduktID` (`ProduktID`),
  CONSTRAINT `produktynafakturze_ibfk_1` FOREIGN KEY (`FakturaID`) REFERENCES `faktura` (`FakturaID`),
  CONSTRAINT `produktynafakturze_ibfk_2` FOREIGN KEY (`ProduktID`) REFERENCES `produkty` (`ProduktID`),
  CONSTRAINT `CONSTRAINT_1` CHECK (`Podatek` >= 0.00 and `Podatek` <= 1.00),
  CONSTRAINT `CONSTRAINT_2` CHECK (`Cena` >= 0.00),
  CONSTRAINT `CONSTRAINT_3` CHECK (`IloscProduktu` >= 0),
  CONSTRAINT `CONSTRAINT_4` CHECK (`KwotaNetto` >= 0.00),
  CONSTRAINT `CONSTRAINT_5` CHECK (`KwotaBrutto` >= 0.00)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

-- Zrzucanie danych dla tabeli invoice.produktynafakturze: ~9 rows (około)
/*!40000 ALTER TABLE `produktynafakturze` DISABLE KEYS */;
INSERT INTO `produktynafakturze` (`PozycjaID`, `ProduktID`, `FakturaID`, `Nazwa`, `Cena`, `IloscProduktu`, `Podatek`, `Jednostka`, `KwotaNetto`, `KwotaBrutto`) VALUES
	(2, 2, 1, 'krowaNa', 666.66, 14, 0.12, 'g.', 9333.24, 10453.2288),
	(3, 3, 1, 'Batat22', 2, 20, 0.23, ' szt.', 40, 49.2),
	(4, 2, 1, 'ziemniakPremium22', 9, 5, 0.23, 'szt.', 45, 55.35),
	(5, 1, 1, '44444441', 1, 10, 0.23, ' szt.', 10, 12.3),
	(6, 1, 1, '44444441', 1, 10, 0.23, ' szt.', 10, 12.3),
	(7, 1, 1, '44444441', 1, 10, 0.23, ' szt.', 10, 12.3),
	(8, 1, 1, '44444441', 1, 100, 0.23, ' szt.', 100, 123),
	(9, 1, 1, '44444441', 1, 100, 0.23, ' szt.', 100, 123),
	(10, 1, 1, '44444441', 1, 100, 0.23, ' szt.', 100, 123),
	(11, 1, 2, '44444441', 1, 10, 0.23, ' szt.', 10, 12.3);
/*!40000 ALTER TABLE `produktynafakturze` ENABLE KEYS */;

-- Zrzut struktury procedura invoice.test
DELIMITER //
CREATE PROCEDURE `test`()
BEGIN



DECLARE dupa INT;

SET dupa = 0;
CALL dodajListaProdukty(1, 2, 10, dupa);
#CALL dodajListaProdukty(1, 1, 100, dupa);
SELECT dupa;

END//
DELIMITER ;

-- Zrzut struktury procedura invoice.usunFaktura
DELIMITER //
CREATE PROCEDURE `usunFaktura`(IN id INT, OUT output INT)
BEGIN

DECLARE EXIT HANDLER FOR sqlwarning, sqlexception
BEGIN
ROLLBACK TO nothingDone;
SET output = 0;
END;


SET output = 0;
start TRANSACTION;
SAVEPOINT nothingDone;
DELETE FROM faktura WHERE faktura.FakturaID = id;
SET output = 1;

commit;

END//
DELIMITER ;

-- Zrzut struktury procedura invoice.usunFirma
DELIMITER //
CREATE PROCEDURE `usunFirma`(IN nip VARCHAR(90), OUT output INT)
BEGIN

DECLARE EXIT HANDLER FOR sqlwarning, sqlexception
BEGIN
ROLLBACK TO nothingDone;
SET output = 0;
END;

SET autocommit = 0;
SET output = 0;
start TRANSACTION;
SAVEPOINT nothingDone;
DELETE FROM firma WHERE firma.NIPFirmy = nip;
SET output = 1;

commit;

END//
DELIMITER ;

-- Zrzut struktury procedura invoice.usunKlient
DELIMITER //
CREATE PROCEDURE `usunKlient`(IN id INT, OUT output INT)
BEGIN

DECLARE EXIT HANDLER FOR sqlwarning, sqlexception
BEGIN
ROLLBACK TO nothingDone;
SET output = 0;
END;


SET output = 0;
start TRANSACTION;
SAVEPOINT nothingDone;
DELETE FROM klienci WHERE klienci.KlientID = id;
SET output = 1;

commit;

END//
DELIMITER ;

-- Zrzut struktury procedura invoice.usunListaProdukty
DELIMITER //
CREATE PROCEDURE `usunListaProdukty`(IN id INT, OUT output INT)
BEGIN

DECLARE EXIT HANDLER FOR sqlwarning, sqlexception
BEGIN
ROLLBACK TO nothingDone;
SET output = 0;
END;

SET autocommit = 0;
SET output = 0;
start TRANSACTION;
SAVEPOINT nothingDone;	

DELETE FROM produktynafakturze WHERE produktynafakturze.PozycjaID = id;
SET output = 1;

commit;

END//
DELIMITER ;

-- Zrzut struktury procedura invoice.usunPracownik
DELIMITER //
CREATE PROCEDURE `usunPracownik`(IN id INT, OUT output INT)
BEGIN

DECLARE EXIT HANDLER FOR sqlwarning, sqlexception
BEGIN
ROLLBACK TO nothingDone;
SET output = 0;
END;


SET output = 0;
SET autocommit = 0;
start TRANSACTION;
SAVEPOINT nothingDone;
DELETE FROM pracownicy WHERE pracownikid = id;
SET output = 1;

commit;

END//
DELIMITER ;

-- Zrzut struktury procedura invoice.usunProdukt
DELIMITER //
CREATE PROCEDURE `usunProdukt`(IN id INT, OUT output INT)
BEGIN

DECLARE EXIT HANDLER FOR sqlwarning, sqlexception
BEGIN
ROLLBACK TO nothingDone;
SET output = 0;
END;


SET output = 0;
SET autocommit = 0;
start TRANSACTION;
SAVEPOINT nothingDone;
DELETE FROM produkty WHERE produktid = id;
SET output = 1;
commit;

END//
DELIMITER ;

-- Zrzut struktury wyzwalacz invoice.delete_faktura
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER delete_faktura AFTER DELETE ON invoice.faktura
FOR EACH ROW
BEGIN

DELETE FROM produktynafakturze
WHERE
    produktynafakturze.FakturaID = OLD.FakturaID;
    
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Zrzut struktury wyzwalacz invoice.delete_insertfakturze
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER delete_insertfakturze AFTER INSERT ON invoice.ProduktyNaFakturze
FOR EACH ROW
BEGIN

UPDATE faktura
SET 
	CalkowitaKwotaBrutto = ROUND((SELECT SUM(KwotaBrutto) FROM ProduktyNaFakturze WHERE ProduktyNaFakturze.FakturaID = faktura.FakturaID), 4),
	CalkowitaKwotaNetto = ROUND((SELECT SUM(KwotaNetto) FROM ProduktyNaFakturze WHERE ProduktyNaFakturze.FakturaID = faktura.FakturaID), 4)
WHERE
    faktura.FakturaID = NEW.FakturaID;
    
    
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Zrzut struktury wyzwalacz invoice.delete_listanafakturze
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER delete_listanafakturze AFTER DELETE ON invoice.ProduktyNaFakturze
FOR EACH ROW
BEGIN

UPDATE faktura
SET 
	CalkowitaKwotaBrutto = ROUND((SELECT SUM(KwotaBrutto) FROM ProduktyNaFakturze WHERE ProduktyNaFakturze.FakturaID = faktura.FakturaID), 4),
	CalkowitaKwotaNetto = ROUND((SELECT SUM(KwotaNetto) FROM ProduktyNaFakturze WHERE ProduktyNaFakturze.FakturaID = faktura.FakturaID), 4)
WHERE
    faktura.FakturaID = OLD.FakturaID;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Zrzut struktury wyzwalacz invoice.delete_updatefakturze
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER delete_updatefakturze AFTER UPDATE ON invoice.ProduktyNaFakturze
FOR EACH ROW
BEGIN

UPDATE faktura
SET 
	CalkowitaKwotaBrutto = ROUND((SELECT SUM(KwotaBrutto) FROM ProduktyNaFakturze WHERE ProduktyNaFakturze.FakturaID = faktura.FakturaID), 4),
	CalkowitaKwotaNetto = ROUND((SELECT SUM(KwotaNetto) FROM ProduktyNaFakturze WHERE ProduktyNaFakturze.FakturaID = faktura.FakturaID), 4)
WHERE
    faktura.FakturaID = NEW.FakturaID;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
