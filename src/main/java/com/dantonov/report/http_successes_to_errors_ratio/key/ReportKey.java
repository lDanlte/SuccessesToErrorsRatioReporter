package com.dantonov.report.http_successes_to_errors_ratio.key;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 *
 * @author Antonov Denis (den007230@gmail.com)
 */
public class ReportKey {

    
    private final String ip;
    private final String resource;

    
    
    public ReportKey(String ip, String resource) {
        this.ip = ip;
        this.resource = resource;
    }

    
    public String getIp() { return ip; }
    
    public String getResource() { return resource; }

    
    @Override
    public int hashCode() {
        
        return new HashCodeBuilder(17, 37)
                .append(ip)
                .append(resource)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        
        if (obj == this) return true;
        if (obj == null) return false;
        if (!(obj instanceof ReportKey)) return false;
        
        ReportKey other = (ReportKey) obj;
        
        return new EqualsBuilder()
                .append(this.ip, other.ip)
                .append(this.resource, other.resource)
                .isEquals();
    }
    
    
    
    
}
