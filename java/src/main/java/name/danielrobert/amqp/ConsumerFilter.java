package name.danielrobert.amqp;

import java.util.EnumSet;
import java.util.Set;

import name.danielrobert.amqp.EtlExecution.Environment;
import name.danielrobert.amqp.EtlExecution.Result;

public class ConsumerFilter {
    
    private final EnumSet<Result> relevantResults;

    public ConsumerFilter(Set<Result> relevantResult) {
        this.relevantResults = EnumSet.copyOf(relevantResult);
    }

    public boolean relevant(EtlExecution exec) {
        return relevantResults.contains(exec.getResult());
    }
    
    public EtlExecution hack(EtlExecution exec) {
        if (Result.SUCCESS == exec.getResult()) {
            EtlExecution newExec;
            try {
                newExec = exec.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
            newExec.setResult(Result.HAXORED);
            return newExec;
        }
        return exec;
    }
    
}
