package com.haohuotong.entity;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.Photo;

public class ContactGenerator {
	private Context context;
	
	private static final String[] PROJECTECTION = {
		Phone.DISPLAY_NAME,
		Phone.NUMBER,
		Photo.PHOTO_ID,
		Phone.CONTACT_ID,
	};
	private static final int DISPLAY_NAME_INDEX = 0;
	private static final int PHONE_NUMBER_INDEX = 1;
	private static final int PHOTO_ID_INDEX = 2;
	private static final int CONTACT_ID_INDEX = 3;
	
	public ContactGenerator(Context context) {
		this.context = context;
	}
	
	public List<ContactInfo> generateList() {
		List<ContactInfo> list = new ArrayList<ContactInfo>();
		
		ContentResolver resolver = context.getContentResolver();
		Cursor cursor = resolver.query(Phone.CONTENT_URI, PROJECTECTION, null, null,  PROJECTECTION[DISPLAY_NAME_INDEX] + " ASC");
		while (cursor.moveToNext()) {
			String name = cursor.getString(DISPLAY_NAME_INDEX);
			String phone = cursor.getString(PHONE_NUMBER_INDEX);
			long photoId = cursor.getLong(PHOTO_ID_INDEX);
			long contactId = cursor.getLong(CONTACT_ID_INDEX);
			
			Bitmap head = null;
			if (photoId > 0) {
				Uri uri = ContentUris.withAppendedId(Contacts.CONTENT_URI, contactId);
				InputStream input = Contacts.openContactPhotoInputStream(resolver, uri);
				head = BitmapFactory.decodeStream(input);
			}
			
			ContactInfo item = new ContactInfo();
			
//			private String name;
//			private String phone;
//			private Bitmap head;
//			private long contactId;
			
			item.setName(name);
			item.setPhone(phone);
			item.setHead(head);
			item.setContactId(contactId);
			list.add(item);
		}
		
		return list;
	}
}
