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
  submitted: boolean = false;
  createmsg: string = 'Successfully submitted. Added new customer.';
  address: Address = new Address;
//  custOffices: Office[] = new Array<Office>();
  offices: Office[] = new Array<Office>();
  hidePartOne: boolean = false;
  hidePartTwo: boolean = true;
  selectedOfficeIds: Map<number, Office>;
  mapOfOffices: Map<number, Office>;

  constructor(private customerService: CustomerService,
    private officeService: OfficeService,
    private router: Router) { }

  ngOnInit() {
    this.officeService.getOfficeList().subscribe(data => {
      console.log(data);
      this.offices = data;
      this.loadMapOfOffices();
    }, error => console.log(error));
  }

  loadMapOfOffices() {
//    var tempOffices = JSON.parse(JSON.stringify(this.offices));
//    this.mapOfOffices = new Map<String, Office>();
//    var counts: number = this.offices.length;
//    for (var x = 0; x < counts; x++) {
//      var anOffice = tempOffices.pop();
//      console.log('Office being loaded to map: ' + anOffice);
///      this.mapOfOffices.set('' + anOffice.id, anOffice);
//    }

    console.log('Contents of Offices[]');
    console.log(this.offices);
    this.mapOfOffices = new Map<number, Office>();
    for (let office of this.offices) {
      console.log('Office entry: ');
      console.log(office);
      this.mapOfOffices.set(office.id, office);
    }

    console.log('Contents of mapOfOffices: Map()...');
    console.log(this.mapOfOffices);
    this.selectedOfficeIds = new Map<number, Office>();
  }

  onChange(officeId: number, isChecked: boolean) {
    console.log('OfficeId: ' + officeId);
    console.log('Is checked? ' + isChecked);
    console.log(officeId);
    if (isChecked) {
      this.selectedOfficeIds.set(officeId, this.mapOfOffices.get(officeId));
      console.log('Contents of officeId: ' + officeId);
      console.log(this.mapOfOffices.get(officeId));
    } else {
      this.selectedOfficeIds.delete(officeId);
    }
  }

  save() {
    this.customer.address = this.address;
    this.customer.offices = new Array<Office>();
    console.log('SelectedOfficeIds...');
    console.log(this.selectedOfficeIds);
    let tempOffices = this.selectedOfficeIds.values();
    for (let anOffice of tempOffices) {
      console.log('Office being selected and loaded...')
      console.log(anOffice);
      this.customer.offices.push(anOffice);
    }

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
