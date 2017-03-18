package org.apache.struts2.views.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;

import org.apache.struts2.components.Component;
import org.apache.struts2.components.Submit;

import com.meng.surveypark.util.ValidateUtil;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * @see Submit
 */
public class SubmitTag extends AbstractClosingTag {

    private static final long serialVersionUID = 2179281109958301343L;

    protected String action;
    protected String method;
    protected String align;
    protected String type;
    protected String src;

    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new Submit(stack, req, res);
    }

    protected void populateParams() {
        super.populateParams();

        Submit submit = ((Submit) component);
        submit.setAction(action);
        submit.setMethod(method);
        submit.setAlign(align);
        submit.setType(type);
        submit.setSrc(src);
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    //重写该方法
    public int doEndTag() throws JspException {
		return hasRight()? super.doEndTag() : SKIP_BODY;
	}

    //重写该方法
    public int doStartTag() throws JspException {
		return hasRight()? super.doStartTag() : SKIP_BODY;
	}
    
    //判断是否有权限
    private boolean hasRight()
    {
    	String namespace = this.getFormNamesapce();
    	String actionName = this.getRealAction();
    	return ValidateUtil.hasRight(namespace, actionName, (HttpServletRequest)pageContext.getRequest(), null);
    }
    
    //获取所在表单的命名空间
    private String getFormNamesapce()
    {
    	Tag pTag = this.getParent();
    	while(pTag != null)
    	{
    		if(pTag instanceof FormTag)
    		{
    			return ((FormTag) pTag).namespace;
    		}
    		else
    		{
    			pTag = pTag.getParent();
    		}
    	}
    	return null;
    }
    
    //获取真实actionName
    private String getRealAction()
    {
    	//如果该submit标签存在action属性
    	if(ValidateUtil.isValidate(action))
    	{
    		return action;
    	}
    	Tag pTag = this.getParent();
    	while(pTag != null)
    	{
    		if(pTag instanceof FormTag)
    		{
    			return ((FormTag) pTag).action;
    		}
    		else
    		{
    			pTag = pTag.getParent();
    		}
    	}
    	return null;
    }
    
}
