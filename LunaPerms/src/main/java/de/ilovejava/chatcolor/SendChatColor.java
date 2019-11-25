package de.ilovejava.chatcolor;

import java.net.Socket;

import de.ilovejava.multi.querys.Query;
import de.ilovejava.utils.Utils;

public class SendChatColor extends Query{
	public SendChatColor(Socket c) {
		for(String s : Utils.getPermissionGroups().keySet()) {
			this.sendChatColor(Utils.getPermissionGroups().get(s).messageColor(), s, c);
		}
		this.ColorSendEnd(c);
	}
}
