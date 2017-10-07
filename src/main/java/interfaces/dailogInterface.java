/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import dialogUtil.pWnd;

/**
 *
 * @author vasil
 */
public interface dailogInterface<T> {
    //public pWnd<T> createDialog();    
    public void show();
    public void close();
    public pWnd<T> getPWnd();
}
