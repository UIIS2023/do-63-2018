package strategy;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class FileLog implements FileChooser{

	@Override
	public void save(Object obj, File fileName) {
		ArrayList<String> saveLogList = (ArrayList<String>) obj;
		try {
			FileWriter fw = new FileWriter(fileName);
			Iterator<String> it = saveLogList.iterator();
			while (it.hasNext()) {
				fw.write(it.next() + "\n");
			}
			fw.close();
		} catch (Exception e) {
			e.getStackTrace();
		}
		
	}

}
