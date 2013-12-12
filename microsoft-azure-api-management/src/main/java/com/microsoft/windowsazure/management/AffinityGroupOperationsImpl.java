// 
// Copyright (c) Microsoft and contributors.  All rights reserved.
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//   http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// 
// See the License for the specific language governing permissions and
// limitations under the License.
// 

// Warning: This code was generated by a tool.
// 
// Changes to this file may cause incorrect behavior and will be lost if the
// code is regenerated.

package com.microsoft.windowsazure.management;

import com.microsoft.windowsazure.management.AffinityGroupOperations;
import com.microsoft.windowsazure.management.ManagementClientImpl;
import com.microsoft.windowsazure.management.OperationResponse;
import com.microsoft.windowsazure.management.models.AffinityGroupCreateParameters;
import com.microsoft.windowsazure.management.models.AffinityGroupGetResponse;
import com.microsoft.windowsazure.management.models.AffinityGroupGetResponse.HostedServiceReference;
import com.microsoft.windowsazure.management.models.AffinityGroupGetResponse.StorageServiceReference;
import com.microsoft.windowsazure.management.models.AffinityGroupListResponse;
import com.microsoft.windowsazure.management.models.AffinityGroupListResponse.AffinityGroup;
import com.microsoft.windowsazure.management.models.AffinityGroupUpdateParameters;
import com.microsoft.windowsazure.services.core.ServiceException;
import com.microsoft.windowsazure.services.core.ServiceOperations;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
* Operations for managing affinity groups beneath your subscription.  (see
* http://msdn.microsoft.com/en-us/library/windowsazure/ee460798.aspx for more
* information)
*/
public class AffinityGroupOperationsImpl implements ServiceOperations<ManagementClientImpl>, AffinityGroupOperations
{
    /**
    * Initializes a new instance of the AffinityGroupOperationsImpl class.
    *
    * @param client Reference to the service client.
    */
    AffinityGroupOperationsImpl(ManagementClientImpl client)
    {
        this.client = client;
    }
    
    private ManagementClientImpl client;
    
    /**
    * Gets a reference to the
    * microsoft.windowsazure.management.ManagementClientImpl.
    */
    public ManagementClientImpl getClient() { return this.client; }
    
    /**
    * The Create Affinity Group operation creates a new affinity group for the
    * specified subscription.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/gg715317.aspx for
    * more information)
    *
    * @param parameters Parameters supplied to the Create Affinity Group
    * operation.
    * @return A standard service response including an HTTP status code and
    * request ID.
    */
    @Override
    public Future<OperationResponse> createAsync(final AffinityGroupCreateParameters parameters)
    {
        return this.getClient().getExecutorService().submit(new Callable<OperationResponse>() { @Override
        public OperationResponse call() throws Exception
        {
            return create(parameters);
        }
         });
    }
    
    /**
    * The Create Affinity Group operation creates a new affinity group for the
    * specified subscription.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/gg715317.aspx for
    * more information)
    *
    * @param parameters Parameters supplied to the Create Affinity Group
    * operation.
    * @return A standard service response including an HTTP status code and
    * request ID.
    */
    @Override
    public OperationResponse create(AffinityGroupCreateParameters parameters) throws ParserConfigurationException, SAXException, TransformerConfigurationException, TransformerException, UnsupportedEncodingException, IOException, ServiceException
    {
        // Validate
        if (parameters == null)
        {
            throw new NullPointerException("parameters");
        }
        if (parameters.getDescription() != null && parameters.getDescription().length() > 1024)
        {
            throw new IllegalArgumentException("parameters.Description");
        }
        if (parameters.getLabel() == null)
        {
            throw new NullPointerException("parameters.Label");
        }
        if (parameters.getLabel().length() > 100)
        {
            throw new IllegalArgumentException("parameters.Label");
        }
        if (parameters.getLocation() == null)
        {
            throw new NullPointerException("parameters.Location");
        }
        if (parameters.getName() == null)
        {
            throw new NullPointerException("parameters.Name");
        }
        
        // Tracing
        
        // Construct URL
        String url = this.getClient().getBaseUri() + "/" + this.getClient().getCredentials().getSubscriptionId() + "/affinitygroups";
        
        // Create HTTP transport objects
        HttpPost httpRequest = new HttpPost(url);
        
        // Set Headers
        httpRequest.setHeader("Content-Type", "application/xml");
        httpRequest.setHeader("x-ms-version", "2013-03-01");
        
        // Serialize Request
        String requestContent = null;
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document requestDoc = documentBuilder.newDocument();
        
        Element createAffinityGroupElement = requestDoc.createElementNS("http://schemas.microsoft.com/windowsazure", "CreateAffinityGroup");
        requestDoc.appendChild(createAffinityGroupElement);
        
        Element nameElement = requestDoc.createElementNS("http://schemas.microsoft.com/windowsazure", "Name");
        nameElement.appendChild(requestDoc.createTextNode(parameters.getName()));
        createAffinityGroupElement.appendChild(nameElement);
        
        Element labelElement = requestDoc.createElementNS("http://schemas.microsoft.com/windowsazure", "Label");
        labelElement.appendChild(requestDoc.createTextNode(new String(Base64.encodeBase64(parameters.getLabel().getBytes()))));
        createAffinityGroupElement.appendChild(labelElement);
        
        if (parameters.getDescription() != null)
        {
            Element descriptionElement = requestDoc.createElementNS("http://schemas.microsoft.com/windowsazure", "Description");
            descriptionElement.appendChild(requestDoc.createTextNode(parameters.getDescription()));
            createAffinityGroupElement.appendChild(descriptionElement);
        }
        
        Element locationElement = requestDoc.createElementNS("http://schemas.microsoft.com/windowsazure", "Location");
        locationElement.appendChild(requestDoc.createTextNode(parameters.getLocation()));
        createAffinityGroupElement.appendChild(locationElement);
        
        DOMSource domSource = new DOMSource(requestDoc);
        StringWriter stringWriter = new StringWriter();
        StreamResult streamResult = new StreamResult(stringWriter);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(domSource, streamResult);
        requestContent = stringWriter.toString();
        StringEntity entity = new StringEntity(requestContent);
        httpRequest.setEntity(entity);
        httpRequest.setHeader("Content-Type", "application/xml");
        
        // Send Request
        HttpResponse httpResponse = null;
        httpResponse = this.getClient().getHttpClient().execute(httpRequest);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode != 201)
        {
            ServiceException ex = ServiceException.createFromXml(httpRequest, requestContent, httpResponse, httpResponse.getEntity());
            throw ex;
        }
        
        // Create Result
        OperationResponse result = null;
        result = new OperationResponse();
        result.setStatusCode(statusCode);
        if (httpResponse.getHeaders("x-ms-request-id").length > 0)
        {
            result.setRequestId(httpResponse.getFirstHeader("x-ms-request-id").getValue());
        }
        
        return result;
    }
    
    /**
    * The Delete Affinity Group operation deletes an affinity group in the
    * specified subscription.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/gg715314.aspx for
    * more information)
    *
    * @param affinityGroupName The name of your affinity group.
    * @return A standard service response including an HTTP status code and
    * request ID.
    */
    @Override
    public Future<OperationResponse> deleteAsync(final String affinityGroupName)
    {
        return this.getClient().getExecutorService().submit(new Callable<OperationResponse>() { @Override
        public OperationResponse call() throws Exception
        {
            return delete(affinityGroupName);
        }
         });
    }
    
    /**
    * The Delete Affinity Group operation deletes an affinity group in the
    * specified subscription.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/gg715314.aspx for
    * more information)
    *
    * @param affinityGroupName The name of your affinity group.
    * @return A standard service response including an HTTP status code and
    * request ID.
    */
    @Override
    public OperationResponse delete(String affinityGroupName) throws IOException, ServiceException
    {
        // Validate
        if (affinityGroupName == null)
        {
            throw new NullPointerException("affinityGroupName");
        }
        
        // Tracing
        
        // Construct URL
        String url = this.getClient().getBaseUri() + "/" + this.getClient().getCredentials().getSubscriptionId() + "/affinitygroups/" + affinityGroupName;
        
        // Create HTTP transport objects
        HttpDelete httpRequest = new HttpDelete(url);
        
        // Set Headers
        httpRequest.setHeader("x-ms-version", "2013-03-01");
        
        // Send Request
        HttpResponse httpResponse = null;
        httpResponse = this.getClient().getHttpClient().execute(httpRequest);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode != 200)
        {
            ServiceException ex = ServiceException.createFromXml(httpRequest, null, httpResponse, httpResponse.getEntity());
            throw ex;
        }
        
        // Create Result
        OperationResponse result = null;
        result = new OperationResponse();
        result.setStatusCode(statusCode);
        if (httpResponse.getHeaders("x-ms-request-id").length > 0)
        {
            result.setRequestId(httpResponse.getFirstHeader("x-ms-request-id").getValue());
        }
        
        return result;
    }
    
    /**
    * The Get Affinity Group Properties operation returns the system properties
    * associated with the specified affinity group.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/ee460789.aspx for
    * more information)
    *
    * @param affinityGroupName The name of the desired affinity group as
    * returned by the name element of the List Affinity Groups operation.
    * @return The Get Affinity Group operation response.
    */
    @Override
    public Future<AffinityGroupGetResponse> getAsync(final String affinityGroupName)
    {
        return this.getClient().getExecutorService().submit(new Callable<AffinityGroupGetResponse>() { @Override
        public AffinityGroupGetResponse call() throws Exception
        {
            return get(affinityGroupName);
        }
         });
    }
    
    /**
    * The Get Affinity Group Properties operation returns the system properties
    * associated with the specified affinity group.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/ee460789.aspx for
    * more information)
    *
    * @param affinityGroupName The name of the desired affinity group as
    * returned by the name element of the List Affinity Groups operation.
    * @return The Get Affinity Group operation response.
    */
    @Override
    public AffinityGroupGetResponse get(String affinityGroupName) throws IOException, ServiceException, ParserConfigurationException, SAXException, URISyntaxException, ParseException
    {
        // Validate
        if (affinityGroupName == null)
        {
            throw new NullPointerException("affinityGroupName");
        }
        
        // Tracing
        
        // Construct URL
        String url = this.getClient().getBaseUri() + "/" + this.getClient().getCredentials().getSubscriptionId() + "/affinitygroups/" + affinityGroupName;
        
        // Create HTTP transport objects
        HttpGet httpRequest = new HttpGet(url);
        
        // Set Headers
        httpRequest.setHeader("x-ms-version", "2013-03-01");
        
        // Send Request
        HttpResponse httpResponse = null;
        httpResponse = this.getClient().getHttpClient().execute(httpRequest);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode != 200)
        {
            ServiceException ex = ServiceException.createFromXml(httpRequest, null, httpResponse, httpResponse.getEntity());
            throw ex;
        }
        
        // Create Result
        AffinityGroupGetResponse result = null;
        // Deserialize Response
        InputStream responseContent = httpResponse.getEntity().getContent();
        result = new AffinityGroupGetResponse();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document responseDoc = documentBuilder.parse(responseContent);
        
        NodeList elements = responseDoc.getElementsByTagName("AffinityGroup");
        Element affinityGroupElement = elements.getLength() > 0 ? ((Element)elements.item(0)) : null;
        if (affinityGroupElement != null)
        {
            NodeList elements2 = affinityGroupElement.getElementsByTagName("Name");
            Element nameElement = elements2.getLength() > 0 ? ((Element)elements2.item(0)) : null;
            if (nameElement != null)
            {
                String nameInstance;
                nameInstance = nameElement.getTextContent();
                result.setName(nameInstance);
            }
            
            NodeList elements3 = affinityGroupElement.getElementsByTagName("Label");
            Element labelElement = elements3.getLength() > 0 ? ((Element)elements3.item(0)) : null;
            if (labelElement != null)
            {
                String labelInstance;
                labelInstance = labelElement.getTextContent() != null ? new String(Base64.decodeBase64(labelElement.getTextContent().getBytes())) : null;
                result.setLabel(labelInstance);
            }
            
            NodeList elements4 = affinityGroupElement.getElementsByTagName("Description");
            Element descriptionElement = elements4.getLength() > 0 ? ((Element)elements4.item(0)) : null;
            if (descriptionElement != null)
            {
                String descriptionInstance;
                descriptionInstance = descriptionElement.getTextContent();
                result.setDescription(descriptionInstance);
            }
            
            NodeList elements5 = affinityGroupElement.getElementsByTagName("Location");
            Element locationElement = elements5.getLength() > 0 ? ((Element)elements5.item(0)) : null;
            if (locationElement != null)
            {
                String locationInstance;
                locationInstance = locationElement.getTextContent();
                result.setLocation(locationInstance);
            }
            
            NodeList elements6 = affinityGroupElement.getElementsByTagName("HostedServices");
            Element hostedServicesSequenceElement = elements6.getLength() > 0 ? ((Element)elements6.item(0)) : null;
            if (hostedServicesSequenceElement != null)
            {
                for (int i1 = 0; i1 < hostedServicesSequenceElement.getElementsByTagName("HostedService").getLength(); i1 = i1 + 1)
                {
                    org.w3c.dom.Element hostedServicesElement = ((org.w3c.dom.Element)hostedServicesSequenceElement.getElementsByTagName("HostedService").item(i1));
                    AffinityGroupGetResponse.HostedServiceReference hostedServiceInstance = new AffinityGroupGetResponse.HostedServiceReference();
                    result.getHostedServices().add(hostedServiceInstance);
                    
                    NodeList elements7 = hostedServicesElement.getElementsByTagName("Url");
                    Element urlElement = elements7.getLength() > 0 ? ((Element)elements7.item(0)) : null;
                    if (urlElement != null)
                    {
                        URI urlInstance;
                        urlInstance = new URI(urlElement.getTextContent());
                        hostedServiceInstance.setUri(urlInstance);
                    }
                    
                    NodeList elements8 = hostedServicesElement.getElementsByTagName("ServiceName");
                    Element serviceNameElement = elements8.getLength() > 0 ? ((Element)elements8.item(0)) : null;
                    if (serviceNameElement != null)
                    {
                        String serviceNameInstance;
                        serviceNameInstance = serviceNameElement.getTextContent();
                        hostedServiceInstance.setServiceName(serviceNameInstance);
                    }
                }
            }
            
            NodeList elements9 = affinityGroupElement.getElementsByTagName("StorageServices");
            Element storageServicesSequenceElement = elements9.getLength() > 0 ? ((Element)elements9.item(0)) : null;
            if (storageServicesSequenceElement != null)
            {
                for (int i2 = 0; i2 < storageServicesSequenceElement.getElementsByTagName("StorageService").getLength(); i2 = i2 + 1)
                {
                    org.w3c.dom.Element storageServicesElement = ((org.w3c.dom.Element)storageServicesSequenceElement.getElementsByTagName("StorageService").item(i2));
                    AffinityGroupGetResponse.StorageServiceReference storageServiceInstance = new AffinityGroupGetResponse.StorageServiceReference();
                    result.getStorageServices().add(storageServiceInstance);
                    
                    NodeList elements10 = storageServicesElement.getElementsByTagName("Url");
                    Element urlElement2 = elements10.getLength() > 0 ? ((Element)elements10.item(0)) : null;
                    if (urlElement2 != null)
                    {
                        URI urlInstance2;
                        urlInstance2 = new URI(urlElement2.getTextContent());
                        storageServiceInstance.setUri(urlInstance2);
                    }
                    
                    NodeList elements11 = storageServicesElement.getElementsByTagName("ServiceName");
                    Element serviceNameElement2 = elements11.getLength() > 0 ? ((Element)elements11.item(0)) : null;
                    if (serviceNameElement2 != null)
                    {
                        String serviceNameInstance2;
                        serviceNameInstance2 = serviceNameElement2.getTextContent();
                        storageServiceInstance.setServiceName(serviceNameInstance2);
                    }
                }
            }
            
            NodeList elements12 = affinityGroupElement.getElementsByTagName("Capabilities");
            Element capabilitiesSequenceElement = elements12.getLength() > 0 ? ((Element)elements12.item(0)) : null;
            if (capabilitiesSequenceElement != null)
            {
                for (int i3 = 0; i3 < capabilitiesSequenceElement.getElementsByTagName("Capability").getLength(); i3 = i3 + 1)
                {
                    org.w3c.dom.Element capabilitiesElement = ((org.w3c.dom.Element)capabilitiesSequenceElement.getElementsByTagName("Capability").item(i3));
                    result.getCapabilities().add(capabilitiesElement.getTextContent());
                }
            }
        }
        
        result.setStatusCode(statusCode);
        if (httpResponse.getHeaders("x-ms-request-id").length > 0)
        {
            result.setRequestId(httpResponse.getFirstHeader("x-ms-request-id").getValue());
        }
        
        return result;
    }
    
    /**
    * The List Affinity Groups operation lists the affinity groups associated
    * with the specified subscription.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/ee460797.aspx for
    * more information)
    *
    * @return The List Affinity Groups operation response.
    */
    @Override
    public Future<AffinityGroupListResponse> listAsync()
    {
        return this.getClient().getExecutorService().submit(new Callable<AffinityGroupListResponse>() { @Override
        public AffinityGroupListResponse call() throws Exception
        {
            return list();
        }
         });
    }
    
    /**
    * The List Affinity Groups operation lists the affinity groups associated
    * with the specified subscription.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/ee460797.aspx for
    * more information)
    *
    * @return The List Affinity Groups operation response.
    */
    @Override
    public AffinityGroupListResponse list() throws IOException, ServiceException, ParserConfigurationException, SAXException, ParseException
    {
        // Validate
        
        // Tracing
        
        // Construct URL
        String url = this.getClient().getBaseUri() + "/" + this.getClient().getCredentials().getSubscriptionId() + "/affinitygroups";
        
        // Create HTTP transport objects
        HttpGet httpRequest = new HttpGet(url);
        
        // Set Headers
        httpRequest.setHeader("x-ms-version", "2013-03-01");
        
        // Send Request
        HttpResponse httpResponse = null;
        httpResponse = this.getClient().getHttpClient().execute(httpRequest);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode != 200)
        {
            ServiceException ex = ServiceException.createFromXml(httpRequest, null, httpResponse, httpResponse.getEntity());
            throw ex;
        }
        
        // Create Result
        AffinityGroupListResponse result = null;
        // Deserialize Response
        InputStream responseContent = httpResponse.getEntity().getContent();
        result = new AffinityGroupListResponse();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document responseDoc = documentBuilder.parse(responseContent);
        
        NodeList elements = responseDoc.getElementsByTagName("AffinityGroups");
        Element affinityGroupsSequenceElement = elements.getLength() > 0 ? ((Element)elements.item(0)) : null;
        if (affinityGroupsSequenceElement != null)
        {
            for (int i1 = 0; i1 < affinityGroupsSequenceElement.getElementsByTagName("AffinityGroup").getLength(); i1 = i1 + 1)
            {
                org.w3c.dom.Element affinityGroupsElement = ((org.w3c.dom.Element)affinityGroupsSequenceElement.getElementsByTagName("AffinityGroup").item(i1));
                AffinityGroupListResponse.AffinityGroup affinityGroupInstance = new AffinityGroupListResponse.AffinityGroup();
                result.getAffinityGroups().add(affinityGroupInstance);
                
                NodeList elements2 = affinityGroupsElement.getElementsByTagName("Name");
                Element nameElement = elements2.getLength() > 0 ? ((Element)elements2.item(0)) : null;
                if (nameElement != null)
                {
                    String nameInstance;
                    nameInstance = nameElement.getTextContent();
                    affinityGroupInstance.setName(nameInstance);
                }
                
                NodeList elements3 = affinityGroupsElement.getElementsByTagName("Label");
                Element labelElement = elements3.getLength() > 0 ? ((Element)elements3.item(0)) : null;
                if (labelElement != null)
                {
                    String labelInstance;
                    labelInstance = labelElement.getTextContent() != null ? new String(Base64.decodeBase64(labelElement.getTextContent().getBytes())) : null;
                    affinityGroupInstance.setLabel(labelInstance);
                }
                
                NodeList elements4 = affinityGroupsElement.getElementsByTagName("Description");
                Element descriptionElement = elements4.getLength() > 0 ? ((Element)elements4.item(0)) : null;
                if (descriptionElement != null)
                {
                    String descriptionInstance;
                    descriptionInstance = descriptionElement.getTextContent();
                    affinityGroupInstance.setDescription(descriptionInstance);
                }
                
                NodeList elements5 = affinityGroupsElement.getElementsByTagName("Location");
                Element locationElement = elements5.getLength() > 0 ? ((Element)elements5.item(0)) : null;
                if (locationElement != null)
                {
                    String locationInstance;
                    locationInstance = locationElement.getTextContent();
                    affinityGroupInstance.setLocation(locationInstance);
                }
                
                NodeList elements6 = affinityGroupsElement.getElementsByTagName("Capabilities");
                Element capabilitiesSequenceElement = elements6.getLength() > 0 ? ((Element)elements6.item(0)) : null;
                if (capabilitiesSequenceElement != null)
                {
                    for (int i2 = 0; i2 < capabilitiesSequenceElement.getElementsByTagName("Capability").getLength(); i2 = i2 + 1)
                    {
                        org.w3c.dom.Element capabilitiesElement = ((org.w3c.dom.Element)capabilitiesSequenceElement.getElementsByTagName("Capability").item(i2));
                        affinityGroupInstance.getCapabilities().add(capabilitiesElement.getTextContent());
                    }
                }
            }
        }
        
        result.setStatusCode(statusCode);
        if (httpResponse.getHeaders("x-ms-request-id").length > 0)
        {
            result.setRequestId(httpResponse.getFirstHeader("x-ms-request-id").getValue());
        }
        
        return result;
    }
    
    /**
    * The Update Affinity Group operation updates the label and/or the
    * description for an affinity group for the specified subscription.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/gg715316.aspx for
    * more information)
    *
    * @param affinityGroupName The name of your affinity group.
    * @param parameters Parameters supplied to the Update Affinity Group
    * operation.
    * @return A standard service response including an HTTP status code and
    * request ID.
    */
    @Override
    public Future<OperationResponse> updateAsync(final String affinityGroupName, final AffinityGroupUpdateParameters parameters)
    {
        return this.getClient().getExecutorService().submit(new Callable<OperationResponse>() { @Override
        public OperationResponse call() throws Exception
        {
            return update(affinityGroupName, parameters);
        }
         });
    }
    
    /**
    * The Update Affinity Group operation updates the label and/or the
    * description for an affinity group for the specified subscription.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/gg715316.aspx for
    * more information)
    *
    * @param affinityGroupName The name of your affinity group.
    * @param parameters Parameters supplied to the Update Affinity Group
    * operation.
    * @return A standard service response including an HTTP status code and
    * request ID.
    */
    @Override
    public OperationResponse update(String affinityGroupName, AffinityGroupUpdateParameters parameters) throws ParserConfigurationException, SAXException, TransformerConfigurationException, TransformerException, UnsupportedEncodingException, IOException, ServiceException
    {
        // Validate
        if (affinityGroupName == null)
        {
            throw new NullPointerException("affinityGroupName");
        }
        if (parameters == null)
        {
            throw new NullPointerException("parameters");
        }
        if (parameters.getDescription() != null && parameters.getDescription().length() > 1024)
        {
            throw new IllegalArgumentException("parameters.Description");
        }
        if (parameters.getLabel() == null)
        {
            throw new NullPointerException("parameters.Label");
        }
        if (parameters.getLabel().length() > 100)
        {
            throw new IllegalArgumentException("parameters.Label");
        }
        
        // Tracing
        
        // Construct URL
        String url = this.getClient().getBaseUri() + "/" + this.getClient().getCredentials().getSubscriptionId() + "/affinitygroups/" + affinityGroupName;
        
        // Create HTTP transport objects
        HttpPut httpRequest = new HttpPut(url);
        
        // Set Headers
        httpRequest.setHeader("Content-Type", "application/xml");
        httpRequest.setHeader("x-ms-version", "2013-03-01");
        
        // Serialize Request
        String requestContent = null;
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document requestDoc = documentBuilder.newDocument();
        
        Element updateAffinityGroupElement = requestDoc.createElementNS("http://schemas.microsoft.com/windowsazure", "UpdateAffinityGroup");
        requestDoc.appendChild(updateAffinityGroupElement);
        
        Element labelElement = requestDoc.createElementNS("http://schemas.microsoft.com/windowsazure", "Label");
        labelElement.appendChild(requestDoc.createTextNode(new String(Base64.encodeBase64(parameters.getLabel().getBytes()))));
        updateAffinityGroupElement.appendChild(labelElement);
        
        if (parameters.getDescription() != null)
        {
            Element descriptionElement = requestDoc.createElementNS("http://schemas.microsoft.com/windowsazure", "Description");
            descriptionElement.appendChild(requestDoc.createTextNode(parameters.getDescription()));
            updateAffinityGroupElement.appendChild(descriptionElement);
        }
        
        DOMSource domSource = new DOMSource(requestDoc);
        StringWriter stringWriter = new StringWriter();
        StreamResult streamResult = new StreamResult(stringWriter);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(domSource, streamResult);
        requestContent = stringWriter.toString();
        StringEntity entity = new StringEntity(requestContent);
        httpRequest.setEntity(entity);
        httpRequest.setHeader("Content-Type", "application/xml");
        
        // Send Request
        HttpResponse httpResponse = null;
        httpResponse = this.getClient().getHttpClient().execute(httpRequest);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode != 200)
        {
            ServiceException ex = ServiceException.createFromXml(httpRequest, requestContent, httpResponse, httpResponse.getEntity());
            throw ex;
        }
        
        // Create Result
        OperationResponse result = null;
        result = new OperationResponse();
        result.setStatusCode(statusCode);
        if (httpResponse.getHeaders("x-ms-request-id").length > 0)
        {
            result.setRequestId(httpResponse.getFirstHeader("x-ms-request-id").getValue());
        }
        
        return result;
    }
}
