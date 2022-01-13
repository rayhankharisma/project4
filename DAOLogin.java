/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.RrentcarUtil;
import pojo.Tblsignup;

/**
 *
 * @author 62857
 */
public class DAOLogin {
   
       public List<Tblsignup> GetProfile (String username) {
        Transaction trans = null;
        Tblsignup DataProfil = new Tblsignup();
        
        List<Tblsignup> user = new ArrayList();
        Session session = RrentcarUtil.getSessionFactory().openSession();
        try {
            trans = session.beginTransaction();
            Query query= session.createQuery("From Tblsignup where username=:uName");
            query.setString("uName", username);
            DataProfil = (Tblsignup) query.uniqueResult();
            user = query.list();
            trans.commit();
        } catch (Exception e) {
            System.out.println("e");
        }
        return user;
   }
    
    
    public List<Tblsignup> getBy (String uName,String uPass)
    {
        Transaction trans = null;
        Tblsignup us = new Tblsignup();
        List<Tblsignup> user = new ArrayList();
        Session session = RrentcarUtil.getSessionFactory().openSession();
        try {
            trans = session.beginTransaction();
            Query query= session.createQuery("From Tblsignup where username=:uName AND password=:uPass");
            query.setString("uName", uName);
            query.setString("uPass", uPass);
            us = (Tblsignup) query.uniqueResult();
            user = query.list();
            trans.commit();
        } catch (Exception e) {
            System.out.println("e");
        }
        return user;
    }

    public void add_user(Tblsignup user)
    {
        Transaction trans = null;
        Session session = RrentcarUtil.getSessionFactory().openSession();
        try {
            trans = session.beginTransaction();
            session.save(user);
            trans.commit();
	System.out.println ("hello");
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

