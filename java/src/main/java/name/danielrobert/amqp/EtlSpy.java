package name.danielrobert.amqp;

import java.util.EnumSet;
import java.util.Set;

import name.danielrobert.amqp.EtlExecution.Environment;
import name.danielrobert.amqp.EtlExecution.Result;

public class EtlSpy {
    
    private final EnumSet<Environment> relevantEnvs;

    public EtlSpy(Set<Environment> relevantEnvironments) {
        this.relevantEnvs = EnumSet.copyOf(relevantEnvironments);
    }

    public boolean relevant(EtlExecution exec) {
        return relevantEnvs.contains(exec.getEnv());
    }
    
    public EtlExecution hack(EtlExecution exec) {
        EtlExecution newExec;
        try {
            newExec = exec.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        newExec.setResult(Result.HAXORED);
        return newExec;
    }
    
}
