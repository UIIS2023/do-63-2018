package strategy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Iterator;

import mvc.DrawingModel;
import shapes.Shape;

public class FileDrawing implements FileChooser {

	@Override
	public void save(Object obj, File fileName) {
		
		DrawingModel model = (DrawingModel) obj;
		
		ObjectOutputStream objectOutputStream = null;
		try {
			objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName));
			objectOutputStream.writeObject(model.getShapesList());
			objectOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
//
//		try {
//			FileWriter fw = new FileWriter(fileName);
//			Iterator<Shape> it = model.getShapesList().iterator();
//			while(it.hasNext()) {
//				fw.write(it.next().toString() +"\n");
//			}
//			fw.close();
//		} catch (Exception e) {
//			e.getStackTrace();
//		}
		
	}

}
