package de.ilovejava.autoinit;

import java.io.File;

import de.ilovejava.configuration.file.YamlConfiguration;
import de.ilovejava.log.Log_Value;
import de.ilovejava.utils.Utils;

public class LoadMultiUser extends Log_Value{
	public LoadMultiUser() {
		int i = 0;
		File f = new File("plugins/LunaPerms/ConUser");
		if(!f.exists()) {return;}
			for(File t : f.listFiles()) {
				YamlConfiguration cfg = YamlConfiguration.loadConfiguration(t);
				String name = cfg.getString("Config.Name");
				String password = cfg.getString("Config.Password");
				Utils.getMulitUser().put(name, password);
				i++;
			}
		this.MultiUserLoad(i);
	}
}
