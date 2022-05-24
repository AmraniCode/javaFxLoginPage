/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tbza.org.datamanagement;

import tbza.org.models.User;

/**
 *
 * @author PC-DEV-CRI
 */
public interface IDataManager {
    
    public boolean connectToData(String connectionString);
    public boolean checkUserExist(User user);
    public User getUserInformation(int userId);
    public boolean insertUser(User user);
}
