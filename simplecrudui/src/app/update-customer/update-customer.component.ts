import { Component, OnInit } from '@angular/core';
import { Customer } from '../Customer';
import { Address } from '../Address';
import { Office } from '../Office';
import { CustomerService } from '../customer.service';
import { OfficeService } from '../office.service';
import { Router, ActivatedRoute } from '@angular/router';
import { SelectOffice } from './SelectOffice';

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
  officeCount: number;
  displayOffices = false;

  assignedOffices: Office[] = new Array<Office>();
  selectedOffices: Map<number, Office>;
  mapOfOffices: Map<number, SelectOffice>;
  temporaryOffices: SelectOffice[];

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
        this.loadOffices();
    }, error => console.log(error));
  }

  loadOffices() {
    console.log('Contents of Offices[]');
    console.log(this.offices);
    console.log(this.assignedOffices);
    this.selectedOffices = new Map<number, Office>();
    for (let office of this.assignedOffices) {
      this.selectedOffices.set(office.id, office);
    }

    this.mapOfOffices = new Map<number, SelectOffice>();
    for (let office of this.offices) {
      console.log('Office entry: ');
      console.log(office);
      let aSelectOffice = new SelectOffice();
      aSelectOffice.id = office.id;
      aSelectOffice.office = office;
      aSelectOffice.isSelected = this.selectedOffices.has(office.id);
      this.mapOfOffices.set(office.id, aSelectOffice);
    }

    console.log('Contents of mapOfOffices: Map()...');
    console.log(this.mapOfOffices);

    let iterOffices = this.mapOfOffices.values();
    this.temporaryOffices = [];
    for (let iterOffice of iterOffices) {
      this.temporaryOffices.push(iterOffice);
    }
  }

  onChange(officeId: number, isChecked: boolean) {
    console.log('OfficeId: ' + officeId);
    console.log('Is checked? ' + isChecked);
    console.log(officeId);
    this.mapOfOffices.get(officeId).isSelected = isChecked;
    if (isChecked) {
      this.selectedOffices.set(officeId, this.mapOfOffices.get(officeId).office);
    } else {
      this.selectedOffices.delete(officeId);
    }
    console.log(this.mapOfOffices.get(officeId));
  }

  save() {
    this.customer.address = this.address;
    this.customer.offices = new Array<Office>();
    console.log('SelectedOffices...');
    console.log(this.selectedOffices);
    let tempOffices = this.selectedOffices.values();
    for (let anOffice of tempOffices) {
      console.log('Office being selected and loaded...')
      console.log(anOffice);
      this.customer.offices.push(anOffice);
    }

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
