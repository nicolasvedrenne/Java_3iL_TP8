
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//File calc = new File("calc.exe");
		File save = new File("save.html");
		lectureFichier(save);
	}
	
	public static void lectureFichier(File fichier) throws IOException {
		if (!fichier.exists()) {
			System.out.println("Le fichier n'existe pas");
			throw new IOException("Le fichier n'existe pas");
		}
		else {
			System.out.println(fichier.getAbsolutePath());
			if(fichier.isDirectory())
				System.out.println("It is a directory");
			else
				System.out.println("It is a file");
			System.out.println("Name= "+fichier.getName());
			System.out.println("Size= "+fichier.length() +" octets");
			System.out.println("Contenu du fichier :");
			DataInputStream dis = new DataInputStream(new FileInputStream(fichier));
			Byte charToPrint = dis.readByte();
			BufferedWriter bw;
			File defaut = new File(System.getProperty("user.dir") + File.separator + "save.bin");
			bw = new BufferedWriter(new FileWriter(defaut));
			while (dis.available()>0) {
				
			   
			  System.out.println(Integer.toBinaryString(charToPrint));
			   bw.write(Integer.toBinaryString(charToPrint));
			   
			   charToPrint = dis.readByte();
			}

			bw.close();
			dis.close();
			
		}
		
	}
	
	public static void saveObjects(ArrayList<Forme> l, File f) throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
		
		for(Forme forme : l) {
			oos.writeObject(forme);
		}
		oos.close();
	}
	
	public static void readObjects(File f) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
		Forme f1 = (Forme) ois.readObject();
		ois.close();
	}

}
