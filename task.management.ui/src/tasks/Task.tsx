import { useEffect, useState } from "react";
import { Form, Modal } from "react-bootstrap";
import AxiosInstance from '../config/axiosInstance';

interface Task {
    taskId: string,
    taskName: string,
    taskDescription: string,
    taskAssignedBy: string,
    taskAssignedFor: string,
    taskAssignedDate: string,
    taskDeadLine: string,
    activeState: boolean
}


const Task: React.FC = () => {

    const currentDate = new Date();
    const formattedDate = currentDate.toDateString();

    const [taskName, setTaskName] = useState('');
    const [taskDescription, setTaskDescription] = useState('');
    const [taskAssignedBy, setTaskAssignedBy] = useState('');
    const [taskAssignedFor, setTaskAssignedFor] = useState('');
    const [taskAssignedDate, setTaskAssignedDate] = useState('');
    const [taskDeadLine, setTaskDeadLine] = useState('');


    const [tasks, setTasks] = useState<Task[]>([]);
    const [updateTaskName, setUpdateTaskName] = useState('');
    const [updateTaskDescription, setUpdateTaskDescription] = useState('');
    const [updateTaskAssignedFor, setUpdateTaskAssignedFor] = useState('');
    const [updateTaskAssignedBy, setUpdateTaskAssignedBy] = useState('');
    const [updateTaskAssignedDate, setUpdateTaskAssignedDate] = useState('');
    const [updateTaskDeadLine, setUpdateTaskDeadLine] = useState('');



    const [updateTaskState, setUpdateTaskState] = useState(true);
    const [selectedTaskId, setSelectedTaskId] = useState('');
    const [modalState, setModalState] = useState<boolean>(false);

    const [taskNameError, setTaskNameError] = useState('');
    const [taskDescriptionError, setTaskDescriptionError] = useState('');
    const [taskAssignedByError, setTaskAssignedByError] = useState('');
    const [taskAssignedForError, setTaskAssignedForError] = useState('');
    const [taskAssignedDateError, setTaskAssignedDateError] = useState('');
    const [taskDeadLineError, setTaskDeadLineError] = useState('');

    const validateTaskName = () => {
        if (!taskName.trim()) {
            setTaskNameError('Task Name is required');
            return false;
        } else {
            setTaskNameError('');
            return true;
        }
    };

    const validateTaskDescription = () => {
        if (!taskDescription.trim()) {
            setTaskDescriptionError('Task Description is required');
            return false;
        } else {
            setTaskDescriptionError('');
            return true;
        }
    };

    const validateTaskAssignedBy = () => {
        if (!taskAssignedBy.trim()) {
            setTaskAssignedByError('Task Assigned By Person is required');
            return false;
        } else {
            setTaskAssignedByError('');
            return true;
        }
    };

    const validateTaskAssignedFor = () => {
        if (!taskAssignedFor.trim()) {
            setTaskAssignedForError('Task Assigned For Person is required');
            return false;
        } else {
            setTaskAssignedForError('');
            return true;
        }
    };

    const validateTaskAssignedDate = () => {
        if (!taskAssignedDate) {
            setTaskAssignedDateError('Task Assigned Date is required');
            return false;
        } else {
            setTaskAssignedDateError('');
            return true;
        }
    };

    const validateTaskDeadLine = () => {
        if (!taskDeadLine) {
            setTaskDeadLineError('Task Deadline is required');
            return false;
        } else {
            setTaskDeadLineError('');
            return true;
        }
    };

    const handleTaskNameChange = (e) => {
        setTaskName(e.target.value);
        validateTaskName();
    };

    const handleTaskDescriptionChange = (e) => {
        setTaskDescription(e.target.value);
        validateTaskDescription();
    };
    const handleTaskAssignedByChange = (e) => {
        setTaskAssignedBy(e.target.value);
        validateTaskAssignedBy();
    };

    const handleTaskAssignedForChange = (e) => {
        setTaskAssignedFor(e.target.value);
        validateTaskAssignedFor();
    };

    const handleTaskAssignedDateChange = (e) => {
        setTaskAssignedDate(e.target.value);
        validateTaskAssignedDate();
    };

    const handleTaskDeadLineChange = (e) => {
        setTaskDeadLine(e.target.value);
        validateTaskDeadLine();
    };
    






    const findAllTasks = async () => {
        try {

            const response = await AxiosInstance.get('/task/get');
            setTasks(response.data.data);
        } catch (error) {
            console.error('Error fetching tasks:');
        }
    };

    useEffect(() => {
        findAllTasks();
    }, []);

    const saveTask = async () => {
        const isNameValid = validateTaskName();
        const isDescriptionValid = validateTaskDescription();
        const isAssignedByValid = validateTaskAssignedBy();
        const isAssignedForValid = validateTaskAssignedFor();
        const isAssignedDateValid = validateTaskAssignedDate();
        const isDeadLineValid = validateTaskDeadLine();

        if (
            isNameValid &&
            isDescriptionValid &&
            isAssignedByValid &&
            isAssignedForValid &&
            isAssignedDateValid &&
            isDeadLineValid
        ) {
            try {
                await AxiosInstance.post('/task/create', {
                    taskName,
                    taskDescription,
                    taskAssignedBy,
                    taskAssignedFor,
                    taskAssignedDate,
                    taskDeadLine

                });
                setTaskName('');
                setTaskDescription('');
                setTaskAssignedBy('');
                setTaskAssignedFor('')
                setTaskAssignedDate('');
                setTaskDeadLine('');
                findAllTasks();
            } catch (e) {
                console.log(e);
            }
        }
    };


    const updateTask = async () => {
        try {
            await AxiosInstance.put('/task/update?taskId=' + selectedTaskId, {
                taskName: updateTaskName,
                taskDescription: updateTaskDescription,
                taskAssignedBy: updateTaskAssignedBy,
                taskAssignedFor: updateTaskAssignedFor,
                taskAssignedDate: updateTaskAssignedDate,
                taskDeadLine: updateTaskDeadLine,
                activeState: updateTaskState
            });
            setModalState(false);
            findAllTasks();

        } catch (e) {
            console.log(e);
        }
    }

    const deleteTask = async (taskId: string) => {
        await AxiosInstance.delete('/task/delete?taskId=' + taskId);
        findAllTasks();

    }

    const loadModal = async (taskId: string) => {
        const task = await AxiosInstance.get('/task/find?taskId=' + taskId);
        console.log(task);
        setSelectedTaskId(task.data.data.taskId);
        setUpdateTaskName(task.data.data.taskName);
        setUpdateTaskDescription(task.data.data.taskDescription);
        setUpdateTaskAssignedBy(task.data.data.taskAssignedBy)
        setUpdateTaskAssignedFor(task.data.data.taskAssignedFor)
        setUpdateTaskAssignedDate(task.data.data.taskAssignedDate)
        setUpdateTaskDeadLine(task.data.data.taskDeadLine)
        setUpdateTaskState(task.data.data.activeState);
        setModalState(true);
    }

    return (
        <>

            <div className="container">
                <div className="row">
                    <div className="col-12 text-center">
                        <h1 className="fs-1 fw-bolder">Task Management System</h1>

                    </div>
                </div>
                <div className="row">
                    <div className="col-12">
                        <div className="mb-3">
                            <label htmlFor="exampleFormControlInput1" className="form-label">Task Name</label>
                            <input
                                value={taskName}
                                onChange={handleTaskNameChange}
                                type="text"
                                className="form-control"
                                id="exampleFormControlInput1"
                                placeholder="Task Name"
                            />
                            <div className="text-danger">{taskNameError}</div>
                        </div>

                    </div>
                </div>

                <div className="row">
                    <div className="col-6">
                        <div className="mb-3">
                            <label htmlFor="exampleFormControlInput1" className="form-label">Task Assigned By</label>
                            <input
                                value={taskAssignedBy}
                                onChange={handleTaskAssignedByChange}
                                type="text"
                                className="form-control"
                                id="exampleFormControlInput1"
                                placeholder="Task Assigned By"
                            />
                            <div className="text-danger">{taskAssignedByError}</div>
                        </div>

                    </div>
                    <div className="col-6">
                        <div className="mb-3">
                            <label htmlFor="exampleFormControlInput1" className="form-label">Task Assigned For</label>
                            <input
                                value={taskAssignedFor}
                                onChange={handleTaskAssignedForChange}
                                type="text"
                                className="form-control"
                                id="exampleFormControlInput1"
                                placeholder="Task Assigned For"
                            />
                            <div className="text-danger">{taskAssignedForError}</div>
                        </div>
                    </div>
                </div>
                <div className="row">
                    <div className="col-6">
                        <div className="mb-3">
                            <label htmlFor="exampleFormControlInput1" className="form-label">Task Assigned Date</label>
                            <Form.Control
                                type="date"
                                value={taskAssignedDate}
                                onChange={handleTaskAssignedDateChange}
                            />
                            <div className="text-danger">{taskAssignedDateError}</div>
                        </div>
                    </div>
                    <div className="col-6">
                        <div className="mb-3">
                            <label htmlFor="exampleFormControlInput1" className="form-label">Task Assigned DeadLine</label>
                            <Form.Control
                                type="date"
                                value={taskDeadLine}
                                onChange={handleTaskDeadLineChange}
                            />
                            <div className="text-danger">{taskDeadLineError}</div>
                        </div>
                    </div>
                </div>

                <div className="row">
                    <div className="col-12">
                        <div className="mb-3">
                            <label htmlFor="exampleFormControlTextarea1" className="form-label">Task Description</label>
                            <textarea
                                value={taskDescription}
                                onChange={handleTaskDescriptionChange}
                                className="form-control"
                                id="exampleFormControlTextarea1"
                                rows={6}
                                placeholder="Task Description"
                            />
                            <div className="text-danger">{taskDescriptionError}</div>
                        </div>
                    </div>
                </div>

                <div className="row">
                    <div className="col-12 d-flex justify-content-end">
                        <button className="btn btn-primary" onClick={saveTask}>Create Task</button>
                    </div>
                </div>

                <hr />


                <div className="row">
                    <div className="col-12 text-end">
                        <p className="fs-5 fw-bolder">{formattedDate}</p>
                    </div>
                </div>

                <div className="row">
                    <div className="col-12">
                        <table className="table table-hover">
                            <thead>
                                <tr>
                                    <th scope="col">Task Id</th>
                                    <th scope="col">Name</th>
                                    <th scope="col">Description</th>
                                    <th scope="col">AssignedBy</th>
                                    <th scope="col">AssignedFor</th>
                                    <th scope="col">AssignedDate</th>
                                    <th scope="col">DeadLine</th>
                                    <th scope="col">Status</th>
                                    <th scope="col">Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                {Array.isArray(tasks) && tasks.length > 0 ? (
                                    tasks.map((task) => (
                                        <tr key={task.taskId}>
                                            <td>{task.taskId}</td>
                                            <td>{task.taskName}</td>
                                            <td>{task.taskDescription}</td>
                                            <td>{task.taskAssignedBy}</td>
                                            <td>{task.taskAssignedFor}</td>
                                            <td>{task.taskAssignedDate}</td>
                                            <td>{task.taskDeadLine}</td>
                                            <td>{task.activeState.toString()}</td>
                                            <td>
                                                <button
                                                    onClick={(e) => {
                                                        loadModal(task.taskId)
                                                    }}
                                                    className="btn btn-warning btn-sm"
                                                >
                                                    UPDATE
                                                </button>{" "}
                                                &nbsp;
                                                <button
                                                    onClick={(e) => {
                                                        if (window.confirm('Are you sure?')) {
                                                            deleteTask(task.taskId)
                                                        }
                                                    }}
                                                    className="btn btn-danger btn-sm"
                                                >
                                                    DELETE
                                                </button>
                                            </td>
                                        </tr>
                                    ))
                                ) : (
                                    <tr>
                                        <td colSpan={5} className="text-center">
                                            {tasks.length === 0 ? 'No tasks found. Add New Tasks' : 'Loading tasks...'}

                                        </td>
                                    </tr>
                                )}
                            </tbody>

                        </table>
                    </div>
                </div>
            </div>

            <Modal show={modalState}>
                <div className="outer p-4">
                    <h2 className="text-center">Update Task</h2>
                    <hr />
                    <div className="col-12">
                        <div className="form-group mb-3">
                            <label htmlFor="name">TaskName</label>
                            <input type="text"
                                onChange={(e) => setUpdateTaskName(e.target.value)}
                                defaultValue={updateTaskName}
                                className="form-control" />
                        </div>
                    </div>
                    <div className="col-12">
                        <div className="form-group mb-3">
                            <label htmlFor="name">Assigned By</label>
                            <input type="text"
                                onChange={(e) => setUpdateTaskAssignedBy(e.target.value)}
                                defaultValue={updateTaskAssignedBy}
                                className="form-control" />
                        </div>
                    </div>
                    <div className="col-12">
                        <div className="form-group mb-3">
                            <label htmlFor="name">Assigned For</label>
                            <input type="text"
                                onChange={(e) => setUpdateTaskAssignedFor(e.target.value)}
                                defaultValue={updateTaskAssignedFor}
                                className="form-control" />
                        </div>
                    </div>
                    <div className="col-12">
                        <div className="mb-3">
                            <label htmlFor="exampleFormControlInput1" className="form-label">Task Assigned Date</label>
                            <Form.Control
                                onChange={(e) => setUpdateTaskAssignedDate(e.target.value)}
                                type="date"
                                defaultValue={updateTaskAssignedDate}

                            />
                        </div>
                    </div>
                    <div className="col-12">
                        <div className="mb-3">
                            <label htmlFor="exampleFormControlInput1" className="form-label">Task DeadLine</label>
                            <Form.Control
                                onChange={(e) => setUpdateTaskDeadLine(e.target.value)}
                                type="date"
                                defaultValue={updateTaskDeadLine}

                            />
                        </div>
                    </div>
                    <div className="col-12">
                        <div className="form-group mb-3">
                            <label htmlFor="description">Description</label>
                            <textarea
                                onChange={(e) => setUpdateTaskDescription(e.target.value)}
                                defaultValue={updateTaskDescription}
                                className="form-control" />
                        </div>
                    </div>
                    <div className="col-12">
                        <div className="form-group mb-3">
                            <label htmlFor="state">Active State</label>
                            <select
                                onChange={(e) => setUpdateTaskState(e.target.value === 'true')}
                                value={updateTaskState.toString()}
                                className="form-select"
                            >
                                <option value="true">True</option>
                                <option value="false">False</option>
                            </select>
                        </div>
                    </div>

                    <div className=" col-12 d-flex justify-content-end gap-1">

                        <button type={"button"} className="btn btn-success btn-sm" onClick={() => updateTask()}>Update Task</button>
                        <button type={"button"} className="btn btn-danger btn-sm" onClick={() => setModalState(false)}>Cancel</button>
                    </div>
                </div>
            </Modal>


        </>
    )
}

export default Task;