import { Component, OnInit } from '@angular/core';
import { Customer } from '../Customer';
import { CustomerService } from '../customer.service';
import { Router } from '@angular/router';
import { OfficeService } from '../office.service';
import { Office } from '../Office';
import { Address } from '../Address';

@Component({
  selector: 'app-add-customer',
  templateUrl: './add-customer.component.html',
  styleUrls: ['./add-customer.component.css']
})
export class AddCustomerComponent implements OnInit {

  customer: Customer = new Customer;
  submitted = false;
  createmsg = 'Successfully submitted. Added new customer.';
  address: Address = new Address;
  offices: Office[] = new Array<Office>();
  hidePartOne = false;
  hidePartTwo = true;

  constructor(private customerService: CustomerService,
    private officeService: OfficeService,
    private router: Router) { }

  ngOnInit() {
    this.officeService.getOfficeList().subscribe(data => {
      console.log(data);
      this.offices = data;
    }, error => console.log(error));
  }

  save() {
    this.customer.address = this.address;
    this.customerService.addCustomer(this.customer).subscribe(
      data => console.log(data), 
      error => {
        console.log(error);
        this.createmsg = 'Error encountered!';
      });
  }

  onSubmit() {
    this.submitted = true;
    this.hidePartOne = true;
    this.hidePartOne = true;
    this.save();
  }

  nextClicked() {
    this.hidePartOne = true;
    this.hidePartTwo = false;
  }

  backClicked() {
    this.hidePartOne = false;
    this.hidePartTwo = true;
  }

  addNew() {
    window.location.reload();
  }
}
