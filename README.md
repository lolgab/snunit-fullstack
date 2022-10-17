# SNUnit fullstack with scala-cli and snunit CLI

This example shows how to use [scala-cli](https://scala-cli.virtuslab.org/) and [snunit-cli](https://github.com/lolgab/snunit-cli).

scala-cli is great to create small Scala projects.

`snunit` cli builds on top of scala-cli to add the ability to install the Scala Native tooling, run and deploy Scala Native HTTP apps using [NGINX Unit](https://unit.nginx.org/)

## Prerequisites

### Install snunit-cli

Download the binary from https://github.com/lolgab/snunit-cli/releases

```
chmod +x snunit
sudo cp snunit /usr/local/bin/
```

### Install dependencies

Run the following command:

```
snunit installTools
```

## How the project is structured

scala-cli doesn't support modules, there is only a module.
To have multiple modules we use a "poor man"'s approach.

We have three directories:
- `backend`
  
  contains server side code which will compiled with Scala Native
- `frontend`
  
  contains frontend code which will compiled with Scala.js
- `shared`
  
  contains files that are system-linked into the other folders
  every system-linked (using `ln -s ../shared/endpoints.scala`)
  file will be shared between the two projects.
  This is particularly useful for API types and endpoints using [SoftwareMill's tapir](https://tapir.softwaremill.com/en/latest/)

### frontend

The frontend is using [Laminar](https://laminar.dev/) and Tapir's client to create a table with the result of the Api call.
Building the frontend means creating a `main.js` inside the `static` folder
(`/static/main.js` is `.gitignore`d) which will be then served by NGINX Unit static file serving.

## How to run

Here you 

## Build the frontend

```
scala-cli package frontend -f -o static/main.js
```

You can also watch the code to continuously build a new `main.js`
on every source code change.
In a separate terminal run:

```
scala-cli package frontend -f -w -o static/main.js
```

### Run backend

snunit run --path backend --static static --port 8080 --no-runtime

## Build production docker image

```
# build the frontend in release mode
scala-cli package frontend -f --js-mode release -o static/main.js
# build the docker image
snunit buildDocker --path backend --static static --port 8080 --no-runtime
````

## VSCode support

Unfortunately scala-cli doesn't support editing two different projects
at the same time with IDE features in VSCode.

What you can do is to launch multiple VSCode windows for backend and frontend
to edit them with Metals support:

```
code frontend
```

or

```
code backend
```