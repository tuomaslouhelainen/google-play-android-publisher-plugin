package org.jenkinsci.plugins.googleplayandroidpublisher;

import com.google.jenkins.plugins.credentials.domains.RequiresDomain;
import hudson.tasks.BuildStepMonitor;
import hudson.tasks.Builder;
import org.kohsuke.stapler.DataBoundSetter;

@RequiresDomain(value = AndroidPublisherScopeRequirement.class)
public abstract class GooglePlayBuilder extends Builder {

    private transient CredentialsHandler credentialsHandler;

    @DataBoundSetter
    private String googleCredentialsId;

    protected CredentialsHandler getCredentialsHandler() {
        if (credentialsHandler == null) {
            credentialsHandler = new CredentialsHandler(googleCredentialsId);
        }
        return credentialsHandler;
    }

    public BuildStepMonitor getRequiredMonitorService() {
        // Try to minimise concurrent editing, as the Google Play Developer Publishing API does not allow it
        return BuildStepMonitor.STEP;
    }

}