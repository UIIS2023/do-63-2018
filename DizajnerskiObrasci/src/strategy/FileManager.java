package strategy;

import java.io.File;

public class FileManager implements FileChooser{
	
	private FileChooser fileCh;
	
	

	public FileManager(FileChooser fileCh) {
		super();
		this.fileCh = fileCh;
	}



	@Override
	public void save(Object obj, File fileName) {
		fileCh.save(obj, fileName);
		
	}

}
