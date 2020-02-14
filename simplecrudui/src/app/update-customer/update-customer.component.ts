import { Component, OnInit } from '@angular/core';
import { Customer } from '../Customer';
import { Address } from '../Address';
import { Office } from '../Office';
import { CustomerService } from '../customer.service';
import { OfficeService } from '../office.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-update-customer',
  templateUrl: './update-customer.component.html',
  styleUrls: ['./update-customer.component.css']
})
export class UpdateCustomerComponent implements OnInit {

  id: number;
  customer: Customer;
  submitted = false;
  updatemsg = 'Customer information has been updated successfully.';
  address: Address;
  offices: Office[] = new Array<Office>();
  hidePartOne = false;
  hidePartTwo = true;
  assignedOffices: Office[] = new Array<Office>();
  officeCount: number;
  displayOffices = false;

  custOffices: Office[] = new Array<Office>();
  selectedOfficeIds: Map<number, Office>;
  mapOfOffices: Map<number, Office>;

  constructor(private route: ActivatedRoute,
    private router: Router, 
    private customerService: CustomerService,
    private officeService: OfficeService) {

  }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    this.officeService.getOfficeList().subscribe(data => {
      console.log(data);
      this.offices = data;
      this.reloadData();
    }, error => {
      console.log(error);
      this.updatemsg = 'Error encountered!';
    });
  }

  reloadData() {
    this.customerService.getCustomer(this.id).subscribe(data => {
        console.log(data);
        this.customer = data;
        this.address = data.address;
        this.assignedOffices = data.offices;
        console.log('Offices: ' + this.offices);
        this.officeCount = data.offices !== null ? data.offices.length : 0;
        this.displayOffices = data.offices !== null && this.officeCount > 0;
    }, error => console.log(error));
  }

  save() {
    this.customer.address = this.address;
    this.customerService.updateCustomer(this.id, this.customer).subscribe(
      data => console.log(data),
      error => {
        console.log(error);
        this.updatemsg = 'Error encountered!';
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

}
