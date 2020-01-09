/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.controller;

import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.wmtrucking.dtos.CommonResponse;
import com.wmtrucking.dtos.InvoiceResponsedto;
import com.wmtrucking.entity.MaDriver;
import com.wmtrucking.entity.MaInvoice;
import com.wmtrucking.entity.MaJobs;
import com.wmtrucking.exceptions.InvalidHeaderException;
import com.wmtrucking.exceptions.InvalidTokenException;
import com.wmtrucking.service.DriverService;
import com.wmtrucking.service.InvoiceService;
import com.wmtrucking.service.JobService;
import com.wmtrucking.utils.AppUtil;
import com.wmtrucking.utils.Constant;
import com.wmtrucking.utils.MaJWT;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.bind.DatatypeConverter;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RequestMapping("/invoice")
@RestController
@Scope("request")
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;
    @Autowired
    AppUtil appUtil;
    @Autowired
    MaJWT maJWT;
    @Autowired
    DriverService driverService;
    @Autowired
    JobService jobService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Object> create(Model model, HttpServletRequest request, @RequestHeader("Authorization") String Authorization,
            @RequestHeader("apptoken") String apptoken, @RequestHeader("devicetoken") String devicetoken, @RequestBody @Valid InvoiceResponsedto invoiceResponsedto)
            throws InvalidHeaderException, InvalidTokenException {
        appUtil.checkHeadersWithAuth(request);
        String id = maJWT.getId(appUtil.getJwt(request), Authorization, devicetoken);
        if (id == null) {
            return new ResponseEntity(new CommonResponse("Your session is expired", null, 0, null), HttpStatus.CREATED);
        }
        Long driverid = Long.valueOf(id);

        MaDriver maDriver = driverService.findById(driverid, Constant.ACTIVE.toString());
        MaJobs maJobs = jobService.findCompletJob(invoiceResponsedto.getJob_id(), Constant.ACTIVE.toString(), driverid, Constant.COMPLETED.toString());
        if (maDriver != null) {
            if (maJobs != null) {
                String toaddress = "<span><b>" + maDriver.getFirstname() + " "
                        + ((maDriver.getMiddlename() != null && !maDriver.getMiddlename().equals("")) ? maDriver.getMiddlename() + " " : "")
                        + maDriver.getLastname() + "</b></span><br/>" + (maDriver.getAddress1() != null ? maDriver.getAddress1() + "<br/>" : "")
                        + (maDriver.getCity() != null ? maDriver.getCity() + "-" : "") + (maDriver.getPincode() != null ? maDriver.getPincode() + "<br/>" : "")
                        + (maDriver.getMobile() != null ? maDriver.getMobile() : "");

                String fromaddress = "<span><b>WM Trucking & Excavating</b></span><br/>25400 Jack Rd, Hockley, TX 77447<br/>United States";
                MaInvoice maInvoice = new MaInvoice();
                maInvoice.setDriverid(maDriver);
                maInvoice.setJobid(maJobs);
                maInvoice.setAmount(invoiceResponsedto.getAmount());
                maInvoice.setComments(invoiceResponsedto.getComments());
                maInvoice.setFromaddress(fromaddress);
                maInvoice.setToaddress(toaddress);
                maInvoice.setStatus(Constant.ACTIVE.toString());
                maInvoice.setCreateddate(new Date());
                maInvoice.setCreatedby(driverid);
                invoiceService.save(maInvoice);
                return new ResponseEntity(new CommonResponse("Invoice has been created successfully ", null, 1, null), HttpStatus.CREATED);
            }
            return new ResponseEntity(new CommonResponse("Invoice hase been already created ", null, 0, null), HttpStatus.CREATED);

        }
        return new ResponseEntity(new CommonResponse("Something went wrong ", null, 0, null), HttpStatus.CREATED);
    }

   

}
