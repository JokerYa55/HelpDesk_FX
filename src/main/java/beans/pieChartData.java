/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author vasil
 */
public class pieChartData {

    private String Name;
    private Float Count;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Float getCount() {
        return Count;
    }

    public void setCount(Float Count) {
        this.Count = Count;
    }

    public pieChartData(String Name, Float Count) {
        this.Name = Name;
        this.Count = Count;
    }

   

}
