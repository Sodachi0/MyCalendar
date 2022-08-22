# MyCalendar

MyCalendar is an availability and reservation management app.

## Installation

Use git to clone the repository or download the zip.

```bash
git clone git@github.com:Sodachi0/MyCalendar.git
```

## You may also install

To make sure MyCalendar work as expected the following tools are needed:

- Java 17
- Docker
- Docker Compose

## Usage

```bash
cd MyCalendar/
./mycalendar.sh
```

The frontend of the application can be found at:
```
http://localhost:4200/
```

The username and password neded to make a new availability or delete one can be found at:
```
vim MyCalendar/calendarmanager/src/main/resources/adminInfo.json
```
This config file has for default value the following:
```json
{
  "username": "admin",
  "password": "password"
}
```
