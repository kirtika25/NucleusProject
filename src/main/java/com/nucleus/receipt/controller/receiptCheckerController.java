package com.nucleus.receipt.controller;

import com.nucleus.receipt.model.Receipt;
import com.nucleus.receipt.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;


/**
 * <p> Serves the receipt overview page </p>
 */
@Controller
public class receiptCheckerController {


    @Autowired
    ReceiptService receiptService;


    /**
     * <p> Get mapping for the receipt overview page. returns the model and view with a list of receipts attached for the
     * datatable on the page. </p>
     * @return the modelAndView for the receipt overview page.
     */
    @PreAuthorize("hasRole('ROLE_CHECKER') or hasRole('ROLE_MAKER')")
    @GetMapping(value = {"/receiptChecker" })
    public ModelAndView receiptChecker(){
        ModelAndView mv = new ModelAndView("views/receipt/receiptChecker");
        mv.addObject("receipts", receiptService.getReceiptList());
        return mv;
    }


    /**
     * <p> Get mapping for receipt approval link. Redirects to a success/error page depending on whether the object
     * update was successful. </p>
     * @param String receiptIdString Path variable with the receipt Id to be updated.
     * @return returns modelAndView of success page if operation was successful, else the error page.
     */
    @PreAuthorize("hasRole('ROLE_CHECKER')")
    @GetMapping(value = "/receiptChecker/approve/{receiptId}")
    public ModelAndView receiptCheckerApprove(@PathVariable("receiptId") String receiptIdString){
        Integer receiptId = Integer.parseInt(receiptIdString);
        Receipt receipt = receiptService.getReceipt(receiptId);
        receipt.setReceiptStatus("Approved");
        Boolean success = receiptService.updateReceipt(receipt);

        if(success){
            ModelAndView mv = new ModelAndView("views/receipt/receiptSuccess");
            mv.addObject("messageHeader", "Receipt was successfully approved");
            mv.addObject("messageBody", "You successfully approved a receipt");
            mv.addObject("receiptNumber", receiptIdString);
            return mv;
        }
        ModelAndView mv = new ModelAndView("views/receipt/receiptError");
        mv.addObject("messageHeader", "Receipt approval failed");
        mv.addObject("messageBody", "There was an error approving this receipt. Please try again");
        mv.addObject("receiptNumber", receiptIdString);
        return mv;
    }


    /**
     * <p> Get mapping for receipt rejection link. Redirects to a success/error page depending on whether the object
     * update was successful. </p>
     * @param String receiptIdString Path variable with the receipt Id to be updated.
     * @return returns modelAndView of success page if operation was successful, else the error page.
     */
    @PreAuthorize("hasRole('ROLE_CHECKER')")
    @GetMapping(value = "/receiptChecker/reject/{receiptId}")
    public ModelAndView receiptCheckerReject(@PathVariable("receiptId") String receiptIdString){
        Integer receiptId = Integer.parseInt(receiptIdString);
        Receipt receipt = receiptService.getReceipt(receiptId);
        receipt.setReceiptStatus("Rejected");
        Boolean success = receiptService.updateReceipt(receipt);
        if(success){
            ModelAndView mv = new ModelAndView("views/receipt/receiptSuccess");
            mv.addObject("messageHeader", "Receipt was successfully rejected");
            mv.addObject("messageBody", "You successfully rejected a receipt");
            mv.addObject("receiptNumber", receiptIdString);
            return mv;
        }
        ModelAndView mv = new ModelAndView("views/receipt/receiptError");
        mv.addObject("messageHeader", "Receipt Rejection Failed");
        mv.addObject("messageBody", "There was an error rejecting this receipt. Please try again");
        mv.addObject("receiptNumber", receiptIdString);
        return mv;
    }


}
