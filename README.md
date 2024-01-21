# Cars Project API

## Introduction
This document describes the RESTful endpoints provided by the Cars Project API. The API offers various functionalities to interact with car data, allowing users to retrieve information based on specific criteria such as age, type, and drive mode of cars.

## API Endpoints

### Base URL
All URLs referenced in the documentation have the following base:
`/api/v1`

### Endpoints Description

#### 1. Get One-Year-Old Automatic Cars
- **URL:** `/one-year-automatic`
- **Method:** `GET`
- **Description:** Retrieve a list of cars that are one year old and have an automatic transmission.
- **Query Parameters:**
    - `isFourWheelDrive` (optional): Boolean value to filter cars that are four-wheel drive.
- **Example Request:**
`GET /api/v1/one-year-automatic?isFourWheelDrive=true`

#### 2. Get Older Diesel Cars
- **URL:** `/older-diesel`
- **Method:** `GET`
- **Description:** Fetch the oldest cars in the system that run on diesel.
- **Query Parameters:**
- `isFourWheelDrive` (optional): Boolean value to filter cars that are four-wheel drive.
- **Example Request:**
`GET /api/v1/older-diesel?isFourWheelDrive=false`

#### 3. Get Three-Year-Old Cars Priced Between 5k and 10k with Automatic Transmission
- **URL:** `/three-year-5k-to-10k-automatic`
- **Method:** `GET`
- **Description:** Retrieve cars that are three years old, priced between 5k and 10k, and have an automatic transmission.
- **Query Parameters:**
- `isFourWheelDrive` (optional): Boolean value to filter cars that are four-wheel drive.
- **Example Request:**
`GET /api/v1/three-year-5k-to-10k-automatic?isFourWheelDrive=true`

## Swagger documentation
To access Swagger documentation, when the Spring Boot app is running go to your browser and navigate to `localhost:8080/swagger-ui.html`