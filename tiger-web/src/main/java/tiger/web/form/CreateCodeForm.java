/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.web.form;

import tiger.core.domain.CodeDomain;

import javax.validation.constraints.NotNull;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 10:02 PM yiliang.gyl Exp $
 */
public class CreateCodeForm {

    @NotNull(message = "this can't be null")
    private String title;

    private String content;

    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public CodeDomain convertToDomain(){
        CodeDomain codeDomain = new CodeDomain();
        codeDomain.setContent(this.getContent());
        codeDomain.setDescription(this.getDescription());
        codeDomain.setTitle(this.getTitle());
        return codeDomain;
    }

}
