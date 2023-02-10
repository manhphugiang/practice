public class CommissionEmployee extends Employee {
    private double grossSales;
    private double commissionRate;
    

    public CommissionEmployee(String firstName, String lastName, String socialSecurityNumber, double grossSales, double commissionRate){
        super(firstName, lastName, socialSecurityNumber);
        
        if(grossSales < 0.0){
            System.out.println("Gross sales must be >= 0.0");
            return ;
        }
        if(commissionRate <= 0.0 || commissionRate >= 1.0) {
            System.out.println("Commission rate must be > 0.0 and < 1.0");
            return ;
        }
        this.grossSales = grossSales;
        this.commissionRate = commissionRate;
    }
    
    public double getGrossSales(){
        return this.grossSales;
    }
    
    public void setGrossSales(double grossSales){
        if(grossSales < 0.0){
            System.out.println("Gross sales must be >= 0.0");
            return;
        }
        this.grossSales = grossSales;
    }
    
    public double getCommissionRate(){
        return this.commissionRate;
    }
    
    public void setCommissionRate(double commissionRate){
        if(commissionRate <= 0.0 || commissionRate >= 1.0){ 
            System.out.println("Commission rate must be > 0.0 and < 1.0");
        return;
        }
        this.commissionRate = commissionRate;
    }
    
    public double earnings(){
        return getCommissionRate() * getGrossSales();
    }
    
    @Override
    public String toString(){
        return String.format("%s: %s %n %s: $%,.2f %n %s: %.2f", 
            " ", super.toString(), 
            "Gross sales", getGrossSales(), 
            "Commission rate", getCommissionRate());
    }
    }

    