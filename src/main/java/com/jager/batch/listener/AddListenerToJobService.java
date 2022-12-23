package com.jager.batch.listener;

import java.util.Set;

import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.job.AbstractJob;
import org.springframework.batch.core.step.AbstractStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.jager.batch.config.BatchProperties;

/**
 * This service adds listeners to jobs. Listeners provided by beans implementing
 * ListenerProvider are added automatically.
 *
 * @author Tobias Flohre
 */
@Component
@ConditionalOnProperty(value = "batch.enable", matchIfMissing = true)
public class AddListenerToJobService {

    private final BatchProperties batchProperties;
    /**
     * All beans implementing ListenerProvider are injected here.
     */
    private final Set<ListenerProvider> listenerProviders;

    private MCDListener loggingListener;

    public AddListenerToJobService(BatchProperties batchProperties,
                                   @Autowired(required = false) Set<ListenerProvider> listenerProviders,
                                   MCDListener loggingListener) {
        this.batchProperties = batchProperties;
        this.listenerProviders = listenerProviders;
        this.loggingListener = loggingListener;
    }

    public void addListenerToJob(AbstractJob job) {
        job.registerJobExecutionListener(loggingListener);
        addListenerToStep(job, loggingListener);

        if (listenerProviders != null) {
            for (ListenerProvider listenerProvider : listenerProviders) {
                for (JobExecutionListener jobExecutionListener : listenerProvider.jobExecutionListeners()) {
                    job.registerJobExecutionListener(jobExecutionListener);
                }

                for (StepExecutionListener stepExecutionListener : listenerProvider.stepExecutionListeners()) {
                    addListenerToStep(job, stepExecutionListener);
                }
            }
        }

    }

    private void addListenerToStep(AbstractJob job, StepExecutionListener loggingListener) {
        if (batchProperties.isEnableStepListener()) {
            for (String stepName : job.getStepNames()) {
                AbstractStep step = (AbstractStep) job.getStep(stepName);
                step.registerStepExecutionListener(loggingListener);
            }
        }
    }
}