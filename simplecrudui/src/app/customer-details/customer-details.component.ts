import { Component, OnInit } from '@angular/core';
import { Customer } from '../Customer';
import { Address } from '../Address';
import { Office } from '../Office';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerService } from '../customer.service';
import { AddressService } from '../address.service';
import { OfficeService } from '../office.service';

@Component({
  selector: 'app-customer-details',
  templateUrl: './customer-details.component.html',
  styleUrls: ['./customer-details.component.css']
})
export class CustomerDetailsComponent implements OnInit {

  id: number;
  customer: Customer;
  address: Address;
  offices: Office[] = new Array<Office>();
  officeCount: number;
  displayOffices = false;

  constructor(private route: ActivatedRoute, private router: Router,
    private customerService: CustomerService,
    private addressService: AddressService,
    private officeService: OfficeService) { }


  ngOnInit() {
    this.customer = new Customer();
    this.id = this.route.snapshot.params['id'];
    this.customerService.getCustomer(this.id).subscribe(data => {
      console.log(data);
      this.customer = data;
      this.address = data.address;
      this.offices = data.offices;
      console.log('Offices: ' + this.offices);
      this.officeCount = data.offices !== null ? data.offices.length : 0;
      console.log('Office count: ' + this.officeCount);
      this.displayOffices = data.offices !== null && this.officeCount > 0;
      console.log('Display office? ' + this.displayOffices);
    }, error => console.log(error));
  }

  list() {
    this.router.navigate(['customers']);
  }

}
