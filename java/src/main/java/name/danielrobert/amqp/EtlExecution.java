package name.danielrobert.amqp;

import java.io.Serializable;
import java.util.Date;
import java.util.regex.Pattern;

public class EtlExecution implements Serializable, Cloneable {
    
    private static final long serialVersionUID = 201411121842L;

    public static enum Result {
        SUCCESS, FAILURE, HAXORED;
    }
    
    public static enum Environment {
        DEVELOPMENT, QA, STAGING, PRODUCTION;
    }

    private Date startingTime;
    private Date endingTime;
    private String product;
    private Result result;
    private Environment env;
    
    public EtlExecution() {
        super();
    }
    
    public String asMessage() {
        return startingTime.getTime()+"|"+endingTime.getTime()+"|"+product+"|"+result.toString()+"|"+env.toString();
    }
    
    public static EtlExecution fromMessage(String msg) {
        String[] parts = msg.split(Pattern.quote("|"));
        EtlExecution retval = new EtlExecution();
        retval.setStartingTime(new Date(Long.parseLong(parts[0])));
        retval.setEndingTime(new Date(Long.parseLong(parts[1])));
        retval.setProduct(parts[2]);
        retval.setResult(Result.valueOf(parts[3]));
        retval.setEnv(Environment.valueOf(parts[4]));
        return retval;
    }

    @Override
    public EtlExecution clone() throws CloneNotSupportedException {
        return (EtlExecution)super.clone();
    }

    public Date getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(Date startingTime) {
        this.startingTime = startingTime;
    }

    public Date getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(Date endingTime) {
        this.endingTime = endingTime;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Environment getEnv() {
        return env;
    }

    public void setEnv(Environment env) {
        this.env = env;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((endingTime == null) ? 0 : endingTime.hashCode());
        result = prime * result + ((env == null) ? 0 : env.hashCode());
        result = prime * result + ((product == null) ? 0 : product.hashCode());
        result = prime * result
                + ((this.result == null) ? 0 : this.result.hashCode());
        result = prime * result
                + ((startingTime == null) ? 0 : startingTime.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EtlExecution other = (EtlExecution) obj;
        if (endingTime == null) {
            if (other.endingTime != null)
                return false;
        } else if (!endingTime.equals(other.endingTime))
            return false;
        if (env != other.env)
            return false;
        if (product == null) {
            if (other.product != null)
                return false;
        } else if (!product.equals(other.product))
            return false;
        if (result != other.result)
            return false;
        if (startingTime == null) {
            if (other.startingTime != null)
                return false;
        } else if (!startingTime.equals(other.startingTime))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "EtlExecution [startingTime=" + startingTime + ", endingTime="
                + endingTime + ", product=" + product + ", result=" + result
                + ", env=" + env + "]";
    }

}
