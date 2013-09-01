package com.example.galleryn;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserDAO {
	
	public static final String NOME_TABELA = "User";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_NAME = "name";
    public static final String COLUNA_LOGIN = "login";
    public static final String COLUNA_PASSWORD = "password";
 
    public static final String SCRIPT_CREATE_TABLE_USERS = "CREATE TABLE " + NOME_TABELA + "("
            + COLUNA_ID + " INTEGER PRIMARY KEY," + COLUNA_NAME + " TEXT," + COLUNA_LOGIN + " TEXT,"
            + COLUNA_PASSWORD + " TEXT" + ")";
    
    public static final String SCRIPT_DELETE_TABLE =  "DROP TABLE IF EXISTS " + NOME_TABELA;
 
    private SQLiteDatabase dataBase = null;
    private static UserDAO instance;
     
    public static UserDAO getInstance(Context context) {
        if(instance == null)
            instance = new UserDAO(context);
        return instance;
    }
 
    private UserDAO(Context context) {
        PersistenceHelper persistenceHelper = PersistenceHelper.getInstance(context);
        dataBase = persistenceHelper.getWritableDatabase();
    }
 
    public void salvar(User user) {
        ContentValues values = gerarContentValuesUser(user);
        dataBase.insert(NOME_TABELA, null, values);
    }
 
    public List<User> recuperarTodos() {
        String queryReturnAll = "SELECT * FROM " + NOME_TABELA;
        Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
        List<User> user= construirUserPorCursor(cursor);
 
        return user;
    }
 
    public void deletar(User user) {
 
        String[] valoresParaSubstituir = {
                String.valueOf(user.getId())
        };
 
        dataBase.delete(NOME_TABELA, COLUNA_ID + " =  ?", valoresParaSubstituir);
    }
 
    public void editar(User user) {
        ContentValues valores = gerarContentValuesUser(user);
 
        String[] valoresParaSubstituir = {
                String.valueOf(user.getId())
        };
 
        dataBase.update(NOME_TABELA, valores, COLUNA_ID + " = ?", valoresParaSubstituir);
    }
 
    public void fecharConexao() {
        if(dataBase != null && dataBase.isOpen())
            dataBase.close(); 
    }
 
 
    private List<User> construirUserPorCursor(Cursor cursor) {
        List<User> users = new ArrayList<User>();
        if(cursor == null)
            return users;
         
        try {
 
            if (cursor.moveToFirst()) {
                do {
 
                    int indexID = cursor.getColumnIndex(COLUNA_ID);
                    int indexName = cursor.getColumnIndex(COLUNA_NAME);
                    int indexLogin = cursor.getColumnIndex(COLUNA_LOGIN);
                    int indexPassword = cursor.getColumnIndex(COLUNA_PASSWORD);
 
                    int id = cursor.getInt(indexID);
                    String name = cursor.getString(indexName);
                    String login = cursor.getString(indexLogin);
                    String password = cursor.getString(indexPassword);
 
                    User user = new User(id, name, login, password);
 
                    users.add(user);
 
                } while (cursor.moveToNext());
            }
             
        } finally {
            cursor.close();
        }
        return users;
    }
 
    private ContentValues gerarContentValuesUser(User user) {
        ContentValues values = new ContentValues();
        values.put(COLUNA_NAME, user.getName());
        values.put(COLUNA_LOGIN, user.getLogin());
        values.put(COLUNA_PASSWORD, user.getPassword());
 
        return values;
    }

}
