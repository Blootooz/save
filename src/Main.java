import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        //Создать три экземпляра класса GameProgress
        GameProgress gameProgress1 = new GameProgress(100, 50, 28, 54);
        GameProgress gameProgress2 = new GameProgress(20, 85, 102, 3);
        GameProgress gameProgress3 = new GameProgress(2, 100, 300, 1);

        //Сохранить сериализованные объекты GameProgress в папку savegames из предыдущей задачи
        saveGame("C:\\Users\\Shhiva\\Documents\\Games\\savegames\\gameProgress1.dat", gameProgress1);
        saveGame("C:\\Users\\Shhiva\\Documents\\Games\\savegames\\gameProgress2.dat", gameProgress2);
        saveGame("C:\\Users\\Shhiva\\Documents\\Games\\savegames\\gameProgress3.dat", gameProgress3);

        List<String> gp = new ArrayList<>();
        gp.add("C:\\Users\\Shhiva\\Documents\\Games\\savegames\\gameProgress1.dat");
        gp.add("C:\\Users\\Shhiva\\Documents\\Games\\savegames\\gameProgress2.dat");
        gp.add("C:\\Users\\Shhiva\\Documents\\Games\\savegames\\gameProgress3.dat");

        //Созданные файлы сохранений из папки savegames запаковать в архив zip
        zipFiles("C:\\Users\\Shhiva\\Documents\\Games\\savegames//zip_output.zip", gp);
        File gameProgress1D = new File("C:\\Users\\Shhiva\\Documents\\Games\\savegames\\gameProgress1.dat");
        File gameProgress2D = new File("C:\\Users\\Shhiva\\Documents\\Games\\savegames\\gameProgress2.dat");
        File gameProgress3D = new File("C:\\Users\\Shhiva\\Documents\\Games\\savegames\\gameProgress3.dat");

        //Удалить файлы сохранений, лежащие вне архива.
        if (gameProgress1D.delete()) System.out.println("Fail \"gameProgress1D\" deleted");
        if (gameProgress2D.delete()) System.out.println("Fail \"gameProgress2D\" deleted");
        if (gameProgress3D.delete()) System.out.println("Fail \"gameProgress3D\" deleted");

    }

    private static void saveGame(String s, GameProgress game) {
        try (FileOutputStream fos = new FileOutputStream(s);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            // запишем экземпляр класса в файл
            oos.writeObject(game);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void zipFiles(String l, List<String> gp) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(l))) {
            for (String arr : gp) {
                try (FileInputStream fis = new FileInputStream(arr)) {
                    ZipEntry entry = new ZipEntry(arr);
                    zout.putNextEntry(entry);
                    while (fis.available() > 0) {
                        zout.write(fis.read());
                    }
                    zout.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
